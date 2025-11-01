package app.revanced.patches.duolingo.unlimitedhearts

import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.extensions.InstructionExtensions.getInstruction
import app.revanced.patcher.patch.bytecodePatch
import com.android.tools.smali.dexlib2.iface.instruction.TwoRegisterInstruction

@Suppress("unused")
val unlimitedHearts = bytecodePatch(
    "Unlimited hearts",
) {
    compatibleWith("com.duolingo"("6.54.5"))

    execute {
        initializeHealthFingerprint.method.apply {
            val insertIndex = initializeHealthFingerprint.patternMatch!!.endIndex
            val register = getInstruction<TwoRegisterInstruction>(insertIndex).registerA

            addInstructions(
                insertIndex,
                "const/4 v$register, 0x1",
            )
        }
    }
}