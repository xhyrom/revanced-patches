package app.revanced.patches.mapycz.unlockpremium

import app.revanced.patcher.fingerprint

internal val userInfoFromJsonFingerprint = fingerprint {
    custom { method, classDef ->
        method.name == "fromJson" && classDef.endsWith("/UserInfo\$Companion;")}
    strings("premium")
}

internal val featuresSyntheticInitFingerprint = fingerprint {
    custom { method, classDef ->
        method.name == "<init>" && classDef.endsWith("/FeaturesApiModel;")}
    parameters("I", "Z", "Z", "Z", "I", "Z", "Z", "Z", "L")
}