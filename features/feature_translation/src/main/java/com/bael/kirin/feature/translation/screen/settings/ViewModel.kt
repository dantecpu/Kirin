package com.bael.kirin.feature.translation.screen.settings

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import com.bael.kirin.feature.translation.configurator.SetupConfigurator
import com.bael.kirin.feature.translation.screen.settings.Intent.AllowPermissionDrawOverlays
import com.bael.kirin.feature.translation.screen.settings.Intent.CheckPermissionDrawOverlays
import com.bael.kirin.feature.translation.screen.settings.Intent.ConfigSetting
import com.bael.kirin.feature.translation.screen.settings.Intent.ConfigSetupFailed
import com.bael.kirin.feature.translation.screen.settings.Intent.ConfigSetupSuccess
import com.bael.kirin.feature.translation.screen.settings.Intent.DenyPermissionDrawOverlays
import com.bael.kirin.lib.arch.base.BaseViewModel
import com.bael.kirin.lib.threading.util.Util.IOThread
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by ErickSumargo on 01/06/20.
 */

@ExperimentalCoroutinesApi
class ViewModel @ViewModelInject constructor(
    @Assisted savedState: SavedStateHandle,
    initState: State,
    initIntent: Intent?,
    private val configurator: SetupConfigurator
) : BaseViewModel<State, Intent>(initState, initIntent, savedState) {

    fun setup() = execute(thread = IOThread) {
        configurator.setup { config ->
            val newIntent = when {
                config.isLoading() -> {
                    ConfigSetting
                }
                config.isError() -> {
                    ConfigSetupFailed(message = config.error?.message.orEmpty())
                }
                else -> {
                    ConfigSetupSuccess
                }
            }
            action(newIntent)
        }
    }

    fun checkPermissionDrawOverlays() = execute {
        val newIntent = CheckPermissionDrawOverlays
        action(newIntent)
    }

    fun allowPermissionDrawOverlays() = execute {
        val newIntent = AllowPermissionDrawOverlays
        action(newIntent)
    }

    fun denyPermissionDrawOverlays() = execute {
        val newIntent = DenyPermissionDrawOverlays
        action(newIntent)
    }
}