package app.revanced.patches.duolingo.unlocksuper

import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.extensions.InstructionExtensions.getInstruction
import app.revanced.patcher.extensions.InstructionExtensions.instructions
import app.revanced.patcher.patch.bytecodePatch
import com.android.tools.smali.dexlib2.iface.instruction.TwoRegisterInstruction

@Suppress("unused")
val unlockDuolingoSuperPath = bytecodePatch(
    "Unlock Duolingo Super",
    use = false
) {
    compatibleWith("com.duolingo"("6.26.2"))

    execute {
        initializeUserFingerprint.method.apply {
            val insertIndex = initializeUserFingerprint.patternMatch!!.endIndex
            val register = getInstruction<TwoRegisterInstruction>(insertIndex).registerA

            addInstructions(
                insertIndex,
                """
                    sget-object v$register, Lcom/duolingo/data/user/SubscriberLevel;->PREMIUM:Lcom/duolingo/data/user/SubscriberLevel;
                """.trimIndent(),
            )

            // TODO: improve
            addInstructions(
                initializeUserFingerprint.method.instructions.size - 1,
                """
                    const/4 v0, 0x1
                    iput-boolean v0, p0, LY9/J;->y:Z
                    iput-boolean v0, p0, LY9/J;->J0:Z
                """.trimIndent()
            )
        }
    }
}