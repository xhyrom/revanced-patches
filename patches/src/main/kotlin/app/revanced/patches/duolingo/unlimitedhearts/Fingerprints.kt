package app.revanced.patches.duolingo.unlimitedhearts

import app.revanced.patcher.fingerprint
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.Opcode

// O8.c
internal val initializeHealthFingerprint = fingerprint {
    accessFlags(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR)
    returns("V")
    parameters(
        "Z", // eligibleForFreeRefill
        "Z", // healthEnabled
        "Z", // useHealth
        "I", // hearts
        "I", // maxHearts
        "I", // secondsPerHeartSegment
        "Ljava/lang/Long;", // nextHeartElapsedRealtimeMs
        "Z", // unlimitedHeartsAvailable
    )
    opcodes(
        Opcode.IPUT_BOOLEAN,
        Opcode.IPUT_BOOLEAN,
        Opcode.IPUT_BOOLEAN,
        Opcode.IPUT,
        Opcode.IPUT,
        Opcode.IPUT,
        Opcode.IPUT_OBJECT,
        Opcode.IPUT_BOOLEAN
    )
}