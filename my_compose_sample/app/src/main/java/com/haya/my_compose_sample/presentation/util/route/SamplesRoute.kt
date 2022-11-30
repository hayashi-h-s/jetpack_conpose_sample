package com.haya.my_compose_sample.presentation.util.route

// SamplesScreenからの画面遷移先一覧
enum class SampleRouteType(val route: String, val title: String) {
    REMEMBER_SCREEN("counter", "remember"),
    TEXT_FIELD_SCREEN("textField", "TextField"),
    LAZY_COLUMN_SCREEN("lazyColumn", "lazyColumn"),
    LAUNCHED_EFFECT_SCREEN("launchedEffect", "launchedEffect"),
    MESSAGE_LIST_SCREEN("messageList", "messageList"),
    DISPOSABLE_EFFECT_SCREEN("disposableEffect", "DisposableEffectScreen"),
    CUSTOM_VIEW_SCREEN("customView","customView"),
    CONSTRAINT_LAYOUT_SCREEN("constraintLayout","constraintLayout"),
    ROOM_SCREEN("roomSampleScreen","Room"),
    BOTTOM_NAVIGATION_SCREEN("bottomNavigationSampleScreen","bottomNavigation"),
    PREVIEW_PARAMETER_SCREEN("previewParameterScreen","previewParameter"),
    PAGER_SCREEN("tabInViewPagerScreen","pater"),
    EXO_PLAYER_SCREEN("exoPlayerSampleScreen","exoPlayer"),
    BOTTOM_SHEET_SCREEN("bottomSheetSampleScreen","bottomSheet"),
    ROTATION_ANIMATION_SCREEN("rotationAnimationScreen","Animation"),
}