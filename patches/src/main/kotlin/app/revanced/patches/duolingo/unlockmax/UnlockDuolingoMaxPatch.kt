package app.revanced.patches.duolingo.unlockmax

import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.extensions.InstructionExtensions.getInstruction
import app.revanced.patcher.patch.bytecodePatch
import com.android.tools.smali.dexlib2.iface.instruction.TwoRegisterInstruction

@Suppress("unused")
val unlockDuolingoMaxPath = bytecodePatch(
    "Unlock Duolingo Max"
) {
    compatibleWith("com.duolingo"("6.26.2"))

    execute {
        initializeUserFingerprint.method.apply {
            val insertIndex = initializeUserFingerprint.patternMatch!!.endIndex
            val register = getInstruction<TwoRegisterInstruction>(insertIndex).registerA

            addInstructions(
                insertIndex,
                """
                    sget-object v$register, Lcom/duolingo/data/user/SubscriberLevel;->GOLD:Lcom/duolingo/data/user/SubscriberLevel;
                """.trimIndent(),
            )
        }
    }
}