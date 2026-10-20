/*
 * SPDX-FileCopyrightText: 2025 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

#define LOG_TAG "vendor.lineage.touch-service.oplus"

#include "HighTouchPollingRate.h"

#include <android-base/file.h>
#include <android-base/logging.h>
#include <android-base/properties.h>

#include <OplusTouchConstants.h>

using ::android::base::GetProperty;
using ::android::base::ReadFileToString;
using ::android::base::WriteStringToFile;

namespace {

constexpr const char* kGameSwitchEnablePath = "/proc/touchpanel/game_switch_enable";
// Optional user-selected report rate mode written when enabling, see HTPR_ENABLE_VALUE.
constexpr const char* kReportRateModeProperty = "persist.sys.oplus.touch.report_rate_mode";

std::string GetEnableValue() {
    const std::string mode = GetProperty(kReportRateModeProperty, "");
    if (!mode.empty() && mode != "0" &&
        mode.find_first_not_of("0123456789") == std::string::npos) {
        return mode;
    }
    return HTPR_ENABLE_VALUE;
}

}  // anonymous namespace

namespace aidl {
namespace vendor {
namespace lineage {
namespace touch {

HighTouchPollingRate::HighTouchPollingRate(std::shared_ptr<IOplusTouch> oplusTouch)
    : mOplusTouch(std::move(oplusTouch)) {}

ndk::ScopedAStatus HighTouchPollingRate::getEnabled(bool* _aidl_return) {
    std::string value;

    if (mOplusTouch) {
        ndk::ScopedAStatus status = mOplusTouch->touchReadNodeFile(
                OplusTouchConstants::DEFAULT_TP_IC_ID, HTPR_ENABLE_NODE, &value);
        if (!status.isOk()) {
            LOG(ERROR) << "Failed to read current AIDL HighTouchPollingRate state";
            return ndk::ScopedAStatus::fromExceptionCode(EX_UNSUPPORTED_OPERATION);
        }
    } else if (!ReadFileToString(kGameSwitchEnablePath, &value)) {
        LOG(ERROR) << "Failed to read current HighTouchPollingRate state";
        return ndk::ScopedAStatus::fromExceptionCode(EX_UNSUPPORTED_OPERATION);
    }

    *_aidl_return = !value.empty() && value[0] != '0';
    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus HighTouchPollingRate::setEnabled(bool enable) {
    if (mOplusTouch) {
        int aidl_return = 0;

        ndk::ScopedAStatus status = mOplusTouch->touchWriteNodeFile(
                OplusTouchConstants::DEFAULT_TP_IC_ID, HTPR_ENABLE_NODE,
                enable ? GetEnableValue() : "0", &aidl_return);
        if (!status.isOk() || aidl_return < 0) {
            LOG(ERROR) << "Failed to write AIDL HighTouchPollingRate state";
            return ndk::ScopedAStatus::fromExceptionCode(EX_UNSUPPORTED_OPERATION);
        }
    } else if (!WriteStringToFile(enable ? "1" : "0", kGameSwitchEnablePath, true)) {
        LOG(ERROR) << "Failed to write HighTouchPollingRate state";
        return ndk::ScopedAStatus::fromExceptionCode(EX_UNSUPPORTED_OPERATION);
    }

    return ndk::ScopedAStatus::ok();
}

}  // namespace touch
}  // namespace lineage
}  // namespace vendor
}  // namespace aidl
