package app.revanced.patches.mapycz.unlockpremium

import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.extensions.InstructionExtensions.getInstruction
import app.revanced.patcher.extensions.InstructionExtensions.addInstruction
import app.revanced.patcher.patch.PatchException
import app.revanced.patcher.patch.bytecodePatch
import com.android.tools.smali.dexlib2.Opcode
import com.android.tools.smali.dexlib2.iface.Method
import com.android.tools.smali.dexlib2.iface.instruction.Instruction
import com.android.tools.smali.dexlib2.iface.instruction.OneRegisterInstruction

@Suppress("unused")
val unlockPremiumPatch = bytecodePatch(
    name = "Unlock Premium Features"
) {
    compatibleWith("cz.seznam.mapy")

    execute {
        userInfoFromJsonFingerprint.method.apply {
            val stringAssignInstructionIndex = userInfoFromJsonFingerprint.stringMatches!!.first().index

            val resultPremiumIndex = indexOfFirstInstructionOrThrow(
                stringAssignInstructionIndex,
                Opcode.MOVE_RESULT,
            )

            val premiumRegister = getInstruction<OneRegisterInstruction>(resultPremiumIndex).registerA

            addInstruction(resultPremiumIndex + 1,
                "const/16 v${premiumRegister}, 0x1")
        }

        featuresSyntheticInitFingerprint.method.addInstructions(0,
            """  
               const/4 p2, 0x1
               const/4 p3, 0x1
               const/4 p4, 0x1
               const/4 p5, -0x1
               const/4 p6, 0x1
               const/4 p7, 0x1
               const/4 p8, 0x1
            """
        )
    }
}

fun Method.indexOfFirstInstruction(startIndex: Int = 0, filter: Instruction.() -> Boolean): Int {
    var instructions = this.implementation!!.instructions
    if (startIndex != 0) {
        instructions = instructions.drop(startIndex)
    }
    val index = instructions.indexOfFirst(filter)

    return if (index >= 0) {
        startIndex + index
    } else {
        -1
    }
}

fun Method.indexOfFirstInstructionOrThrow(startIndex: Int = 0, targetOpcode: Opcode): Int = indexOfFirstInstructionOrThrow(startIndex) {
    opcode == targetOpcode
}

fun Method.indexOfFirstInstructionOrThrow(startIndex: Int = 0, filter: Instruction.() -> Boolean): Int {
    val index = indexOfFirstInstruction(startIndex, filter)
    if (index < 0) {
        throw PatchException("Could not find instruction index")
    }

    return index
}