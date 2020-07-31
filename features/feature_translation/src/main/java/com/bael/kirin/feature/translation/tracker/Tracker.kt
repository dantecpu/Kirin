package com.bael.kirin.feature.translation.tracker

import com.bael.kirin.lib.tracker.contract.Tracker
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/06/20.
 */

class Tracker @Inject constructor(tracker: Tracker) : Tracker by tracker {

    fun trackToggleService(active: Boolean) {
        trackIncrement(event = "$TRACK_TOGGLE_SERVICE/$active")
    }

    fun trackPreferenceUpdate(key: String, active: Boolean) {
        trackIncrement(event = "$TRACK_PREFERENCE_UPDATE/$key/$active")
    }

    fun trackToggleActivation(active: Boolean) {
        trackIncrement(event = "$TRACK_TOGGLE_ACTIVATION/$active")
    }

    fun trackSwapLanguage(sourceLanguage: String, targetLanguage: String) {
        track(
            event = TRACK_SWAP_LANGUAGE,
            value = mapOf(
                "sl" to sourceLanguage,
                "tl" to targetLanguage
            )
        )
    }

    fun trackClearQuery() {
        trackIncrement(event = TRACK_CLEAR_QUERY)
    }

    fun trackTranslationData(
        sourceLanguage: String,
        targetLanguage: String,
        query: String
    ) {
        track(
            event = TRACK_TRANSLATION,
            value = mapOf(
                "sl" to sourceLanguage,
                "tl" to targetLanguage,
                "query" to query
            )
        )
    }

    fun trackStopEditingByBackPressed() {
        trackIncrement(event = "$TRACK_STOP_EDITING/back_pressed")
    }

    fun trackStopEditingByKeyboard() {
        trackIncrement(event = "$TRACK_STOP_EDITING/keyboard")
    }

    fun trackDisplayResultDetail(
        sourceLanguage: String,
        targetLanguage: String,
        query: String,
        result: String
    ) {
        track(
            event = TRACK_DISPLAY_RESULT_DETAIL,
            value = mapOf(
                "sl" to sourceLanguage,
                "tl" to targetLanguage,
                "query" to query,
                "result" to result
            )
        )
    }

    fun trackInstantTranslate(query: String) {
        track(
            event = TRACK_INSTANT_TRANSLATE,
            value = mapOf("query" to query)
        )
    }

    fun trackDeferredInstantTranslate() {
        trackIncrement(event = TRACK_DEFERRED_INSTANT_TRANSLATE)
    }

    fun trackShowContextMenu() {
        trackIncrement(event = TRACK_SHOW_CONTEXT_MENU)
    }

    companion object {
        const val TRACK_TOGGLE_SERVICE: String = "toggle_service"
        const val TRACK_PREFERENCE_UPDATE: String = "preference_update"
        const val TRACK_TOGGLE_ACTIVATION: String = "toggle_activation"
        const val TRACK_SWAP_LANGUAGE: String = "swap_language"
        const val TRACK_CLEAR_QUERY: String = "clear_query"
        const val TRACK_TRANSLATION: String = "translation"
        const val TRACK_STOP_EDITING: String = "stop_editing"
        const val TRACK_DISPLAY_RESULT_DETAIL: String = "display_result_detail"
        const val TRACK_INSTANT_TRANSLATE: String = "instant_translate"
        const val TRACK_DEFERRED_INSTANT_TRANSLATE: String = "deferred_instant_translate"
        const val TRACK_SHOW_CONTEXT_MENU: String = "show_context_menu"
    }
}