package app.revanced.patches.mapycz.spoof

import app.revanced.patcher.fingerprint

val runSecurityChecksFingerprint = fingerprint {
    custom { method, _ ->  method.name == "runSecurityChecks"}
}