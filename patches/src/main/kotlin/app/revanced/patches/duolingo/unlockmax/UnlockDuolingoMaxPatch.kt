package app.revanced.patches.duolingo.unlockmax

import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.extensions.InstructionExtensions.getInstruction
import app.revanced.patcher.extensions.InstructionExtensions.instructions
import app.revanced.patcher.patch.bytecodePatch
import com.android.tools.smali.dexlib2.iface.instruction.ReferenceInstruction
import com.android.tools.smali.dexlib2.iface.instruction.TwoRegisterInstruction

@Suppress("unused")
val unlockDuolingoMaxPath = bytecodePatch(
    "Unlock Duolingo Max"
) {
    compatibleWith("com.duolingo"("6.35.0"))

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

            // TODO: improve
            addInstructions(
                initializeUserFingerprint.method.instructions.size - 1,
                """
                    const/4 v0, 0x1
                    iput-boolean v0, p0, Lhb/H;->y:Z
                    iput-boolean v0, p0, Lhb/H;->L0:Z
                    iput-boolean v0, p0, Lhb/H;->M0:Z
                """.trimIndent()
            )
        }

        initializeSubscriptionFeatureGroup.method.apply {
            val insertIndex = initializeSubscriptionFeatureGroup.patternMatch!!.endIndex
            val register = getInstruction<TwoRegisterInstruction>(insertIndex).registerA
            
            addInstructions(
                insertIndex,
                """
                    new-instance v0, Ljava/util/HashSet;
                    invoke-direct {v0}, Ljava/util/HashSet;-><init>()V
                    
                    sget-object v$register, Lcom/duolingo/data/subscription/max/SubscriptionFeatures;->CAN_ADD_SECONDARY_MEMBERS:Lcom/duolingo/data/subscription/max/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                    
                    sget-object v$register, Lcom/duolingo/data/subscription/max/SubscriptionFeatures;->CHAT_TUTORS:Lcom/duolingo/data/subscription/max/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                    
                    sget-object v$register, Lcom/duolingo/data/subscription/max/SubscriptionFeatures;->EXPLAIN_MY_ANSWER:Lcom/duolingo/data/subscription/max/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                    
                    sget-object v$register, Lcom/duolingo/data/subscription/max/SubscriptionFeatures;->LEGENDARY_LEVEL:Lcom/duolingo/data/subscription/max/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                    
                    sget-object v$register, Lcom/duolingo/data/subscription/max/SubscriptionFeatures;->MASTERY_QUIZ:Lcom/duolingo/data/subscription/max/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                    
                    sget-object v$register, Lcom/duolingo/data/subscription/max/SubscriptionFeatures;->MISTAKES_INBOX:Lcom/duolingo/data/subscription/max/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                    
                    sget-object v$register, Lcom/duolingo/data/subscription/max/SubscriptionFeatures;->NO_NETWORK_ADS:Lcom/duolingo/data/subscription/max/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                    
                    sget-object v$register, Lcom/duolingo/data/subscription/max/SubscriptionFeatures;->NO_SUPER_PROMOS:Lcom/duolingo/data/subscription/max/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                    
                    sget-object v$register, Lcom/duolingo/data/subscription/max/SubscriptionFeatures;->OFFLINE:Lcom/duolingo/data/subscription/max/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                    
                    sget-object v$register, Lcom/duolingo/data/subscription/max/SubscriptionFeatures;->ROLEPLAY_FOR_INTERMEDIATE_LEARNERS:Lcom/duolingo/data/subscription/max/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                    
                    sget-object v$register, Lcom/duolingo/data/subscription/max/SubscriptionFeatures;->SKILL_TEST_OUT:Lcom/duolingo/data/subscription/max/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                    
                    sget-object v$register, Lcom/duolingo/data/subscription/max/SubscriptionFeatures;->STREAK_REPAIR:Lcom/duolingo/data/subscription/max/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                    
                    sget-object v$register, Lcom/duolingo/data/subscription/max/SubscriptionFeatures;->UNLIMITED_HEARTS:Lcom/duolingo/data/subscription/max/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                    
                    sget-object v$register, Lcom/duolingo/data/subscription/max/SubscriptionFeatures;->VIDEO_CALL_IN_PATH:Lcom/duolingo/data/subscription/max/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                    
                    sget-object v$register, Lcom/duolingo/data/subscription/max/SubscriptionFeatures;->VIDEO_CALL_IN_PRACTICE_HUB:Lcom/duolingo/data/subscription/max/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                
                    move-object p1, v0
                """.trimIndent()
            )
        }
    }
}