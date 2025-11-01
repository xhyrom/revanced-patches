package app.revanced.patches.duolingo.unlockmax

import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.extensions.InstructionExtensions.getInstruction
import app.revanced.patcher.extensions.InstructionExtensions.instructions
import app.revanced.patcher.patch.bytecodePatch
import com.android.tools.smali.dexlib2.iface.instruction.TwoRegisterInstruction

@Suppress("unused")
val unlockDuolingoMaxPath = bytecodePatch(
    "Unlock Duolingo Max"
) {
    compatibleWith("com.duolingo"("6.54.5"))

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
                    iput-boolean v0, p0, LVd/K;->y:Z
                    iput-boolean v0, p0, LVd/K;->L0:Z
                    iput-boolean v0, p0, LVd/K;->M0:Z
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
                    
                    sget-object v$register, Lcom/duolingo/core/subscription/models/SubscriptionFeatures;->CAN_ADD_SECONDARY_MEMBERS:Lcom/duolingo/core/subscription/models/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                    
                    sget-object v$register, Lcom/duolingo/core/subscription/models/SubscriptionFeatures;->CHAT_TUTORS:Lcom/duolingo/core/subscription/models/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                    
                    sget-object v$register, Lcom/duolingo/core/subscription/models/SubscriptionFeatures;->EXPLAIN_MY_ANSWER:Lcom/duolingo/core/subscription/models/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                    
                    sget-object v$register, Lcom/duolingo/core/subscription/models/SubscriptionFeatures;->LEGENDARY_LEVEL:Lcom/duolingo/core/subscription/models/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                    
                    sget-object v$register, Lcom/duolingo/core/subscription/models/SubscriptionFeatures;->MASTERY_QUIZ:Lcom/duolingo/core/subscription/models/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                    
                    sget-object v$register, Lcom/duolingo/core/subscription/models/SubscriptionFeatures;->MISTAKES_INBOX:Lcom/duolingo/core/subscription/models/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                    
                    sget-object v$register, Lcom/duolingo/core/subscription/models/SubscriptionFeatures;->NO_NETWORK_ADS:Lcom/duolingo/core/subscription/models/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                    
                    sget-object v$register, Lcom/duolingo/core/subscription/models/SubscriptionFeatures;->NO_SUPER_PROMOS:Lcom/duolingo/core/subscription/models/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                    
                    sget-object v$register, Lcom/duolingo/core/subscription/models/SubscriptionFeatures;->OFFLINE:Lcom/duolingo/core/subscription/models/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                    
                    sget-object v$register, Lcom/duolingo/core/subscription/models/SubscriptionFeatures;->ROLEPLAY_FOR_INTERMEDIATE_LEARNERS:Lcom/duolingo/core/subscription/models/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                    
                    sget-object v$register, Lcom/duolingo/core/subscription/models/SubscriptionFeatures;->SKILL_TEST_OUT:Lcom/duolingo/core/subscription/models/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                    
                    sget-object v$register, Lcom/duolingo/core/subscription/models/SubscriptionFeatures;->STREAK_REPAIR:Lcom/duolingo/core/subscription/models/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                    
                    sget-object v$register, Lcom/duolingo/core/subscription/models/SubscriptionFeatures;->UNLIMITED_HEARTS:Lcom/duolingo/core/subscription/models/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                    
                    sget-object v$register, Lcom/duolingo/core/subscription/models/SubscriptionFeatures;->VIDEO_CALL_IN_PATH:Lcom/duolingo/core/subscription/models/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                    
                    sget-object v$register, Lcom/duolingo/core/subscription/models/SubscriptionFeatures;->VIDEO_CALL_IN_PRACTICE_HUB:Lcom/duolingo/core/subscription/models/SubscriptionFeatures;
                    invoke-interface {v0, v$register}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
                
                    move-object p1, v0
                """.trimIndent()
            )
        }
    }
}