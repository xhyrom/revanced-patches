package app.revanced.patches.duolingo.unlockmax

import app.revanced.patcher.fingerprint
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.Opcode

internal val initializeUserFingerprint = fingerprint {
    accessFlags(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR)
    returns("V")
    strings("betaStatus", "subscriberLevel", "from(...)")
    opcodes(
        Opcode.IPUT_OBJECT,
        Opcode.MOVE_FROM16,
        Opcode.IPUT_BOOLEAN,
        Opcode.MOVE_OBJECT_FROM16,
        Opcode.IPUT_OBJECT,
        Opcode.IPUT_OBJECT
    )
}

internal val initializeSubscriptionFeatureGroup = fingerprint {
    accessFlags(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR)
    returns("V")
    strings("subscriptionFeatures")
    opcodes(
        Opcode.CONST_STRING,
        Opcode.INVOKE_STATIC,
        Opcode.INVOKE_DIRECT,
        Opcode.IPUT_OBJECT
    )
    custom { method, classDef -> classDef.endsWith("q0;") }
}