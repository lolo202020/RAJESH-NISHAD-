package com.example.service

import com.example.model.*

object MasterpiecePresets {

    val mumbaiMonsoonEncounter = ProductionPackage(
        id = "preset_monsoon_romance",
        input = ProjectInput(
            topic = "Two estranged lovers meet by chance in a South Mumbai vintage cafe during a sudden monsoon downpour",
            script = "AARAV: तुम आज भी वही चाय पीती हो?\nMEERA: आदतें कहाँ बदलती हैं आरव... बस लोग बदल जाते हैं।\n(Aarav looks away, swallowing bitter unspoken regret. Meera's fingers tighten around the ceramic cup.)",
            videoType = "Romantic Drama",
            language = "Hindi (Urdu Nuance)",
            targetAudience = "Adults 20-38 & Indie Cinema Fans",
            duration = "30 seconds",
            aspectRatio = "9:16 Vertical",
            visualStyle = "Photorealistic Cinematic 35mm, Wong Kar-Wai Mood",
            charactersHint = "Aarav (31, melancholic architect) & Meera (29, independent book editor)",
            locationHint = "Vintage Colaba Cafe with rainwater trickling down arched glass windows",
            targetAiTool = "Google Veo / Sora",
            budgetLevel = "Studio Quality"
        ),
        brief = ProjectBrief(
            title = "The Monsoon Reminiscence (बारिश और अधूरी बातें)",
            objective = "Evoke raw nostalgic longing and suppressed emotional tension between two people who loved each other deeply but drifted apart.",
            genre = "Poetic Romantic Drama",
            targetAudience = "Romantic drama viewers, short cinema connoisseurs",
            durationAndRatio = "30 seconds | 9:16 Vertical Reel/Short Format",
            visualStyle = "Atmospheric 35mm film grain, moody amber key light, cool rain exterior bokeh, subtle motion blur",
            loglineSummary = "Trapped by sudden Mumbai rain in an old cafe, former lovers Aarav and Meera exchange ten unspoken words that resurrect five years of quiet heartbreak."
        ),
        scriptIntelligence = ScriptIntelligence(
            storyStructure = StoryStructure(
                beginning = "Exterior rain drenched streets cut to close-up of steaming cutting chai and trembling fingers.",
                middle = "Aarav breaks the painful silence with a seemingly casual question; Meera's response exposes deep emotional scar tissue.",
                climax = "Intense eye-lock where neither speaks: Aarav's throat swallows regret, Meera looks down fighting a tear.",
                resolution = "Wide cafe silhouette through rain-streaked window as lightning gently illuminates their unresolved distance."
            ),
            emotionalGraph = listOf(
                EmotionalPoint("00:00", 25, "Nostalgic Silence", "Ambient rain, distance and hesitation between subjects"),
                EmotionalPoint("00:08", 45, "Vulnerable Probe", "Aarav speaks; voice cracked with restrained warmth"),
                EmotionalPoint("00:16", 75, "Emotional Piercing", "Meera delivers subtext-heavy reply, micro-tension in jaw"),
                EmotionalPoint("00:24", 95, "Peak Internal Grief", "Shared silence, swallow of throat, breaking eye contact"),
                EmotionalPoint("00:30", 60, "Poetic Resignation", "Rain outside reflects unfinished closure")
            ),
            characterPsychology = listOf(
                CharacterPsychology(
                    characterName = "Aarav Sharma",
                    personality = "Thoughtful, observant, emotionally guarded",
                    motivation = "Desperately seeking validation if she ever truly moved on",
                    coreFear = "That he was easily forgotten and replaced",
                    coreDesire = "A single glance acknowledging their past love",
                    internalState = "Guilt-ridden, aching under a calm facade"
                ),
                CharacterPsychology(
                    characterName = "Meera Sen",
                    personality = "Fiercely proud, expressive eyes, vulnerable heart",
                    motivation = "Protecting her reconstructed peace of mind",
                    coreFear = "Breaking down in front of the person who broke her heart",
                    coreDesire = "To show strength while secretly hoping he explains why he left",
                    internalState = "Struggling against an overwhelming wave of suppressed memory"
                )
            ),
            subtextAnalysis = listOf(
                SubtextEntry(
                    character = "Aarav",
                    spokenDialogue = "तुम आज भी वही चाय पीती हो?",
                    literalMeaning = "Do you still drink the exact same tea?",
                    hiddenSubtext = "I remember every small detail about you, and thinking of you has never stopped."
                ),
                SubtextEntry(
                    character = "Meera",
                    spokenDialogue = "आदतें कहाँ बदलती हैं आरव... बस लोग बदल जाते हैं।",
                    literalMeaning = "Habits don't change... only people do.",
                    hiddenSubtext = "I remained faithful to what we were; you were the one who walked away."
                )
            ),
            turningPoints = listOf(
                "Aarav's hand trembling slightly before setting his spectacles on the distressed wooden table.",
                "Meera's refusal to look him straight in the eyes until her final devastating sentence."
            ),
            scriptImprovements = listOf(
                "Keep 1.5 seconds of total silence between Aarav's line and Meera's reply to let the emotional weight land.",
                "Add a sensory focal pull from the rainwater droplets on glass to Aarav's reflection."
            )
        ),
        characterBibles = listOf(
            CharacterBibleEntry(
                characterId = "CHAR_AARAV",
                name = "Aarav Sharma",
                visualAge = "31 years old",
                facialFeatures = "Chiseled South Asian features, light stubble, tired warm brown eyes with dark rings of late nights",
                hairAndMakeup = "Tousled damp dark wavy hair, natural matte skin with faint rain mist",
                bodyPosture = "Slightly hunched forward, defensive shoulders, leaning toward Meera but holding back",
                costumeDetails = "Damp charcoal wool overcoat over open-collar beige linen shirt, vintage silver wristwatch",
                voicePattern = "Low baritone, gentle acoustic rasp, pauses before key words",
                actingPattern = "Micro-tightening of jaw, looks down briefly before speaking, never exaggerates gestures",
                aiReferencePrompt = "A realistic 31yo South Asian man, sharp melancholic face, light stubble, damp dark wavy hair, wearing charcoal overcoat and beige linen shirt, subdued emotional gaze"
            ),
            CharacterBibleEntry(
                characterId = "CHAR_MEERA",
                name = "Meera Sen",
                visualAge = "29 years old",
                facialFeatures = "Striking expressive almond eyes, prominent cheekbones, subtle beauty mark near collarbone",
                hairAndMakeup = "Jet black hair pinned casually in an artisanal clip, minimal kajal slightly smudged from rain moisture",
                bodyPosture = "Rigid upright spine, arms wrapped closely around her terracotta mug for emotional armor",
                costumeDetails = "Deep indigo handcrafted cotton kurta with brass buttons, oxidized silver jhumkas",
                voicePattern = "Clear, calm, but laced with a faint tremor on the final syllable",
                actingPattern = "Firm eye contact that suddenly breaks to look outside when overwhelmed, guarded lips",
                aiReferencePrompt = "A photorealistic 29yo South Asian woman, expressive almond eyes, high cheekbones, dark hair pinned casually, indigo cotton kurta, holding terracotta tea cup, restrained poignant expression"
            )
        ),
        environmentBibles = listOf(
            EnvironmentBibleEntry(
                locationName = "Cafe Leopold Heritage Corner",
                environmentType = "Interior Vintage Bistro during Monsoon",
                architecturalDetails = "Distressed teak wood panelling, high ceiling with lazy antique brass fan, arched colonial window",
                propsAndFurniture = "Rustic marble tabletop, vintage ceramic cups, amber table lamp, rainy glass window with neon blur",
                atmosphericMood = "Heavy romantic melancholia, steam rising from cups, damp atmospheric reflections",
                aiEnvironmentPrompt = "Interior of vintage 1940s colonial South Mumbai cafe, dark teak wood walls, arched glass window with heavy monsoon rainwater streaking down, warm glowing Edison lamp on rustic table, cinematic 35mm photography"
            )
        ),
        scenePlans = listOf(
            ScenePlan(
                sceneNumber = "Scene 01",
                sceneTitle = "The Monsoon Stalemate",
                narrativePurpose = "Establish the unexpected collision of their past and the insurmountable wall of silence.",
                emotionalTone = "Quiet acoustic tension, romantic sorrow",
                keyLighting = "Warm 3200K tungsten lamp on tabletop, contrast with cool 5600K rain light from exterior",
                cameraStyle = "Intimate shallow depth of field, slow intentional creeping push-ins, zero jerky movement",
                actingFocus = "Eyes and throat movements rather than overt theatrical gestures"
            )
        ),
        shotList = listOf(
            DirectorShot(
                shotId = "SHOT_01",
                sceneId = "Scene 01",
                timeCode = "00:00 - 00:05",
                durationSec = 5,
                shotType = "Medium Close-Up (Over-The-Shoulder)",
                cameraAngle = "Eye-Level (Slightly Low)",
                cameraMovement = "Slow Cinematic Push-In (2 inches/sec)",
                lensSuggestion = "50mm Anamorphic f/1.4",
                composition = "Aarav in right third profile, Meera in soft background bokeh, framed by antique window frame",
                charactersPresent = "Aarav & Meera",
                characterPosition = "Aarav seated across small table, looking intently at tea steam",
                facialExpression = "Guarded, eyes flickering with unspoken memory, brows slightly contracted",
                bodyLanguage = "Hands clasped around cold water glass, weight shifted forward",
                dialogue = "AARAV: तुम आज भी वही चाय पीती हो?",
                voiceDirection = "Whisper-soft baritone, hesitating on 'तुम', natural throat catch",
                lighting = "Soft warm key light (3200K) skimming Aarav's cheekbone, cool rain reflection on side",
                background = "Blurred cafe interior with warm amber bokeh and rain streaks on window pane",
                colorGrade = "Kodak 5219 film stock simulation, rich deep blacks, warm skin tones",
                soundDesign = "Rhythmic monsoon rain pouring against window glass, distant taxi horn muffled, clink of porcelain spoon",
                transition = "Slow cut on Aarav's blink to Shot 02",
                continuityNotes = "Aarav's overcoat wet on left shoulder; steam rising from both cups",
                aiVideoPrompt = "A photorealistic medium close-up of a 31-year-old South Asian man with damp dark hair and light stubble seated in a vintage Mumbai cafe. He speaks softly, his lips moving in natural Hindi lip sync with a slight pause. His eyes carry restrained longing. Raindrops streak across the dark arched window behind him. Soft warm amber tungsten key light from camera left, cinematic shallow depth of field, steady filmic camera slowly pushing in.",
                avoidList = "Fast camera moves, cartoonish smile, plastic skin, distorted hands, morphing objects, modern bright lighting"
            ),
            DirectorShot(
                shotId = "SHOT_02",
                sceneId = "Scene 01",
                timeCode = "00:05 - 00:11",
                durationSec = 6,
                shotType = "Extreme Close-Up (Reaction Shot)",
                cameraAngle = "Eye-Level Straight",
                cameraMovement = "Locked-Off Static (Zero Motion)",
                lensSuggestion = "85mm Macro f/2.0",
                composition = "Centered framing on Meera's eyes and upper lips, negative space softly out of focus",
                charactersPresent = "Meera",
                characterPosition = "Seated upright, holding ceramic mug near chin",
                facialExpression = "Stuns for a fraction of second; lower lip firms before delivering line with calm gravity",
                bodyLanguage = "Knuckles whiten slightly around the cup; chin lifts half an inch in defense",
                dialogue = "MEERA: आदतें कहाँ बदलती हैं आरव...",
                voiceDirection = "Measured, quiet, icy composure hiding a tempest underneath",
                lighting = "Reflected warm glow from table lamp illuminating her dark irises, single tear held back",
                background = "Dark mahogany cafe wall with faint antique picture frame in extreme blur",
                colorGrade = "Deep indigo contrast with golden skin highlight, 35mm grain",
                soundDesign = "Subtle swell of solo cello note underneath continuous rain ambient",
                transition = "Straight cut on her head tilt to Shot 03",
                continuityNotes = "Steam curling up from her cup into frame; hair strand across right cheek",
                aiVideoPrompt = "A cinematic extreme close-up of a 29-year-old South Asian woman with expressive dark eyes holding a vintage terracotta tea cup. She pauses for 1.5 seconds, eyes shifting slightly as she absorbs the question, then speaks with restrained dignity in realistic Hindi lip synchronization. Natural eye blink timing, subtle tension in jawline, soft golden side lighting, 85mm portrait lens, photorealistic film texture.",
                avoidList = "Melodramatic crying, twitchy eye movement, synthetic hair, sudden lighting changes, jittery camera"
            ),
            DirectorShot(
                shotId = "SHOT_03",
                sceneId = "Scene 01",
                timeCode = "00:11 - 00:18",
                durationSec = 7,
                shotType = "Close-Up (Reverse Shot)",
                cameraAngle = "Over-The-Shoulder Dirty Angle",
                cameraMovement = "Micro Handheld Float (Organic Breathing Rhythm)",
                lensSuggestion = "50mm Prime f/1.8",
                composition = "Aarav in sharp focus, Meera's dark silhouette framing the right edge",
                charactersPresent = "Aarav & Meera",
                characterPosition = "Aarav receives the stinging line",
                facialExpression = "The forced smile vanishes; his eyes dart down to the table, throat visibly swallows",
                bodyLanguage = "Shoulders sink half an inch, exhaling a quiet sigh through parted lips",
                dialogue = "MEERA (O.S.): ...बस लोग बदल जाते हैं।",
                voiceDirection = "Voice-over trailing off into silence as the rain outside intensifies",
                lighting = "Raindrops casting moving water shadow caustics across Aarav's forehead",
                background = "Monsoon storm washing over Mumbai streetlamp visible through window",
                colorGrade = "Desaturated greens and rich amber, cinema contrast ratio 4:1",
                soundDesign = "Sudden roll of low distant monsoon thunder, heavy rain patter, Aarav's soft intake of breath",
                transition = "Cross dissolve into Shot 04",
                continuityNotes = "Aarav's hand has moved from glass to tabletop; tea cup level matched",
                aiVideoPrompt = "A photorealistic dirty over-the-shoulder close-up of a handsome 31-year-old South Asian man listening in heavy silence. His face absorbs emotional pain: eyes drop, his jaw clenches, and his throat swallows regret in a realistic human micro-expression. Water droplet shadows ripple across his forehead from the rainy window. Organic subtle handheld camera, cinematic lighting, masterclass acting realism.",
                avoidList = "Exaggerated grimace, cartoonish sadness, camera shake, low resolution, warped features"
            ),
            DirectorShot(
                shotId = "SHOT_04",
                sceneId = "Scene 01",
                timeCode = "00:18 - 00:25",
                durationSec = 7,
                shotType = "Insert Detail Shot",
                cameraAngle = "High Angle 45-degree",
                cameraMovement = "Slow Tilt Down",
                lensSuggestion = "90mm Macro f/2.8",
                composition = "Diagonal composition of table center: two tea cups separated by wooden grain gap",
                charactersPresent = "Hands of Aarav & Meera",
                characterPosition = "Aarav's fingers inch 1 cm forward on table, then stop before crossing the line",
                facialExpression = "N/A (Focus on hand acting and body language tension)",
                bodyLanguage = "Fingers tense, curl inward with reluctance, symbolizing unresolved distance",
                dialogue = "[Silence - Only the sound of rain and breathing]",
                voiceDirection = "Muted silence",
                lighting = "Warm pool of light illuminating table wood texture, raindrops sparkling on glass edge",
                background = "Dark cafe floor in soft bokeh",
                colorGrade = "Warm golden brown palette with deep shadows",
                soundDesign = "Drip of single rainwater drop from ceiling gutter outside, delicate clink of cup against saucer",
                transition = "Cut to final wide establishing resolution",
                continuityNotes = "Cups positioned exactly as previous shots; liquid level consistent",
                aiVideoPrompt = "A cinematic macro insert shot of a vintage wooden cafe table in a rainstorm. Two steaming cutting chai glasses sit 10 inches apart. A man's hand with a vintage silver watch inches forward slightly toward the woman's delicate hand holding a terracotta cup, then stops with hesitating restraint. Deep wooden texture, warm amber pool of light, slow cinematic tilt, ultra-photorealistic.",
                avoidList = "Extra fingers, mutated hands, abrupt camera jumps, blurriness, modern tableware"
            ),
            DirectorShot(
                shotId = "SHOT_05",
                sceneId = "Scene 01",
                timeCode = "00:25 - 00:30",
                durationSec = 5,
                shotType = "Wide Exterior-to-Interior Silhouette",
                cameraAngle = "Eye-Level Exterior Window POV",
                cameraMovement = "Slow Pull-Back through Water Streaks",
                lensSuggestion = "35mm Cine Lens f/2.8",
                composition = "Both characters silhouetted inside the glowing cafe window, heavy rain covering foreground",
                charactersPresent = "Aarav & Meera (Silhouettes)",
                characterPosition = "Sitting across each other, looking at each other through the warm glowing glass",
                facialExpression = "Subtle silhouettes, postures conveying deep shared history",
                bodyLanguage = "Still, contemplative, frozen in the monsoon afternoon",
                dialogue = "[Music Cue: Melancholy violin resolves into gentle rain ambient]",
                voiceDirection = "Instrumental closure",
                lighting = "Warm glowing yellow interior framing the figures against the dark wet blue Mumbai exterior",
                background = "South Mumbai heritage street, wet asphalt reflecting amber streetlights",
                colorGrade = "Dual-tone: Amber interior vs Deep Blue/Cyan exterior",
                soundDesign = "Full rich monsoon rain shower ambient, city street ambiance, music fade out",
                transition = "Fade to black",
                continuityNotes = "Matches character seating and rainy environment perfectly",
                aiVideoPrompt = "A cinematic wide shot looking through a rain-streaked arched glass window from outside a vintage cafe. Heavy monsoon raindrops run down the glass in foreground. Inside, a man and a woman sit silhouetted against a warm glowing amber interior, facing each other in quiet contemplation. Beautiful blue and orange color grading, slow pull back, cinematic film masterpiece.",
                avoidList = "CGI water artifacts, flickering lights, erratic camera zoom, video glitch"
            )
        ),
        dialogueReactionSteps = listOf(
            DialogueSequenceStep("Step 1: The Initiation", "Aarav takes a breath, glances at Meera's cup, and asks with hesitating familiarity.", "Seeking a bridge across five years of silence.", "Over-the-shoulder Medium Close-up"),
            DialogueSequenceStep("Step 2: The Absorption", "Meera hears his voice; her eyes blink twice, jaw tightens before words leave her mouth.", "The instinctive defense mechanism of an unresolved wound.", "Extreme Close-up on eyes"),
            DialogueSequenceStep("Step 3: The Counter-Punch", "Meera delivers her line looking straight into his soul without blinking.", "Asserting dignity while punishing him with the truth.", "Medium Close-up"),
            DialogueSequenceStep("Step 4: The Internal Collapse", "Aarav lowers his head, unable to sustain her gaze; throat swallows.", "Admitting defeat and bearing the full weight of remorse.", "Tight Close-up"),
            DialogueSequenceStep("Step 5: The Sacred Silence", "Both look at the rain; neither moves to leave.", "Love that cannot be rekindled, but cannot be erased either.", "High Angle Table Insert to Wide")
        ),
        editingSoundPlan = EditingSoundPlan(
            rhythmPacing = "Slow, deliberate, allowing 1.5 to 2 seconds of silence between speech to build immense dramatic tension.",
            dialogueSyncRule = "Dialogue must initiate after micro-expressions begin, ensuring voice matches throat and jaw movements perfectly.",
            ambientSound = "Continuous layered Mumbai monsoon rain (heavy downpour on tin shed, soft patter on glass, street gutters).",
            foleyProps = "Porcelain spoon resting on saucer, rustle of wet wool coat, low hum of antique ceiling fan.",
            musicalScoreCues = listOf(
                "00:00 - Solo acoustic cello sustains a low D-minor note",
                "00:15 - Faint emotional piano chords enter softly behind Meera's dialogue",
                "00:26 - String harmony resolves into gentle rain fade"
            ),
            criticalSilenceMoment = "00:14 to 00:17 (Between 'आरव' and 'बस लोग बदल जाते हैं') — zero music, only the breathing of the characters and rain.",
            transitionPacing = "Match cuts on blinks and natural head movements, no flashy wipes or digital effects."
        ),
        qualityChecklist = listOf(
            QualityCheckItem("Acting", "Eye dart timing and blink rate feel organic, not robotic or fixed", true, "Validated: 2-3 natural blinks per 5s shot"),
            QualityCheckItem("Acting", "Subtext is clearly visible in body language before dialogue starts", true, "Knuckles tighten on cup; shoulders sink"),
            QualityCheckItem("Cinematography", "Lighting ratio consistent across reverse angles (Tungsten 3200K / Cool 5600K)", true, "3:1 key-to-fill ratio maintained"),
            QualityCheckItem("Cinematography", "Lens focal lengths selected with purpose (50mm intimacy, 85mm portrait, 35mm wide)", true, "Proper cinematic optical choices"),
            QualityCheckItem("Continuity", "Costumes, tea cups, water streaks, and seating positions locked across all 5 shots", true, "Zero prop displacement"),
            QualityCheckItem("Story", "Emotional graph peaks at 00:24 and provides satisfying poetic payoff", true, "High dramatic impact in 30 seconds")
        ),
        fullMasterExport = "UNIVERSAL AI FILM DIRECTOR PRODUCTION PACKAGE\nTitle: The Monsoon Reminiscence\nFormat: 9:16 Vertical Reel (30 Seconds)\nStyle: 35mm Photorealistic Cinematic\n..."
    )

    val horrorCorridorKnock = ProductionPackage(
        id = "preset_horror_13th_floor",
        input = ProjectInput(
            topic = "A night security guard investigates a rhythmic knocking sound inside an abandoned hospital floor at 3:17 AM",
            script = "VIKRAM: (into walkie-talkie) कंट्रोल रूम, फ्लोर 13 पर कोई है... दरवाजा अंदर से बंद है।\n(The rhythmic knocking stops suddenly. Then three slow knocks come from the ceiling directly above him.)",
            videoType = "Psychological Horror & Thriller",
            language = "Hindi",
            targetAudience = "Horror fans & Thriller community",
            duration = "30 seconds",
            aspectRatio = "16:9 Landscape",
            visualStyle = "Desaturated Green & Shadow Noir, David Fincher aesthetic",
            charactersHint = "Vikram (42, weary night guard, flashlight trembling)",
            locationHint = "Dilapidated hospital corridor, peeling teal paint, flickering fluorescent tube",
            targetAiTool = "Kling AI / Runway Gen-3",
            budgetLevel = "High-End Cinematic"
        ),
        brief = ProjectBrief(
            title = "Floor 13: The Ceiling Knock (तेरहवीं मंजिल)",
            objective = "Generate spine-chilling atmospheric dread through sound anticipation, shadows, and realistic fear acting.",
            genre = "Atmospheric Supernatural Horror",
            targetAudience = "Psychological horror enthusiasts",
            durationAndRatio = "30 seconds | 16:9 Widescreen",
            visualStyle = "Desaturated clinical teal and pitch black shadows, harsh flashlight beam cutting dust motes",
            loglineSummary = "When night guard Vikram inspects an abandoned ward, the source of the knock shifts from behind the locked door to right above his head."
        ),
        scriptIntelligence = ScriptIntelligence(
            storyStructure = StoryStructure(
                beginning = "Slow tracking shot down an endless corridor as rhythmic wooden thuds echo.",
                middle = "Vikram reaches door 1304; whispers into static-filled walkie-talkie with trembling fingers.",
                climax = "The knocking ceases abruptly. A chilling pause of absolute silence before three distinct thuds drop from the ceiling tile above.",
                resolution = "Vikram's flashlight beam slowly swivels straight up to reveal cracked acoustic tiles lifting."
            ),
            emotionalGraph = listOf(
                EmotionalPoint("00:00", 40, "Eerie Anticipation", "Footsteps and rhythmic distant knocking"),
                EmotionalPoint("00:09", 65, "Rising Panic", "Flashlight beam wavers, breath becomes ragged"),
                EmotionalPoint("00:18", 85, "False Relief", "Knocking stops, dead silence"),
                EmotionalPoint("00:24", 100, "Apex Terror", "Ceiling knocks; eyes widen in petrified horror"),
                EmotionalPoint("00:30", 90, "Unresolved Dread", "Slow vertical flashlight reveal")
            ),
            characterPsychology = listOf(
                CharacterPsychology(
                    characterName = "Vikram Rathore",
                    personality = "Pragmatic ex-serviceman, physically tough but unnerved by the supernatural",
                    motivation = "Finish his mandatory 3 AM patrol and survive the shift",
                    coreFear = "Losing control and being trapped in darkness",
                    coreDesire = "Rational explanations for unexplainable sounds",
                    internalState = "Adrenaline spike, fighting instincts to sprint away"
                )
            ),
            subtextAnalysis = listOf(
                SubtextEntry(
                    character = "Vikram",
                    spokenDialogue = "कंट्रोल रूम, फ्लोर 13 पर कोई है...",
                    literalMeaning = "Control room, someone is on Floor 13.",
                    hiddenSubtext = "I am terrified, please answer me so I know I'm not alone in this hell."
                )
            ),
            turningPoints = listOf(
                "The sudden death of the walkie-talkie static into pitch-black silence.",
                "The shift of the sound source from horizontal (door) to vertical (ceiling)."
            ),
            scriptImprovements = listOf(
                "Do not show any monster face; keep the terror purely in Vikram's pupil dilation and ceiling movement.",
                "Use high-frequency audio drop right before the ceiling knock."
            )
        ),
        characterBibles = listOf(
            CharacterBibleEntry(
                characterId = "CHAR_VIKRAM",
                name = "Vikram Rathore",
                visualAge = "42 years old",
                facialFeatures = "Weathered face, deep forehead creases, sweat beads along hairline, wide terrified eyes",
                hairAndMakeup = "Short cropped salt-and-pepper hair, pale sweaty complexion",
                bodyPosture = "Defensive low stance, heavy boots stepping silently, flashlight held in FBI tactical grip",
                costumeDetails = "Dark navy security guard uniform, brass badge tarnished, heavy leather belt with keys",
                voicePattern = "Hoarse, rushed whisper through clenched teeth",
                actingPattern = "Pupil dilation, chest heaving with shallow breaths, swallowing dry throat",
                aiReferencePrompt = "A realistic 42yo South Asian security guard in navy uniform holding a tactical flashlight in a dark corridor, sweating forehead, terrified wide eyes, high suspense cinema"
            )
        ),
        environmentBibles = listOf(
            EnvironmentBibleEntry(
                locationName = "Floor 13 Abandoned Ward",
                environmentType = "Decaying Medical Facility at Night",
                architecturalDetails = "Flickering overhead tube light, water-damaged ceiling tiles, peeling teal wall paint",
                propsAndFurniture = "Rusted gurney in corner, locked metal door numbered '1304', hanging wires",
                atmosphericMood = "Suffocating claustrophobia, stagnant damp air, floating dust particles in flashlight beam",
                aiEnvironmentPrompt = "Dilapidated dark hospital hallway at night, peeling greenish paint, rusted door marked 1304, hanging electrical wires, atmospheric fog, photorealistic horror lighting"
            )
        ),
        scenePlans = listOf(
            ScenePlan("Scene 01", "The Unseen Presence", "Deliver a masterclass in psychological horror without cheap jumpscares.", "Oppressive dread", "Hard directional flashlight beam cutting darkness", "Slow creeping dolly and Dutch angle", "Sensory horror acting")
        ),
        shotList = listOf(
            DirectorShot(
                shotId = "SHOT_01",
                sceneId = "Scene 01",
                timeCode = "00:00 - 00:07",
                durationSec = 7,
                shotType = "Low Angle Tracking Shot",
                cameraAngle = "Low Angle Floor Level",
                cameraMovement = "Slow Dolly Forward following heavy boots",
                lensSuggestion = "24mm Ultra-Wide Cine Lens",
                composition = "Boots stepping into puddles, corridor vanishing into pure black in distance",
                charactersPresent = "Vikram",
                characterPosition = "Walking slowly along corridor",
                facialExpression = "Alert, tense jaw, eyes darting left and right",
                bodyLanguage = "Right hand gripping heavy Maglite, thumb poised on switch",
                dialogue = "[Sound of distant rhythmic knocking: THUD... THUD... THUD...]",
                voiceDirection = "Ambient tension",
                lighting = "Single flickering fluorescent tube 20 meters away, deep shadows",
                background = "Endless hallway with decaying door frames",
                colorGrade = "Desaturated sickly green, high contrast",
                soundDesign = "Slow heavy boot squeaks on vinyl floor, distant muffled knocking on heavy wood",
                transition = "Cut to Shot 02 on flashlight sweep",
                continuityNotes = "Flashlight beam angled at 45 degrees toward floor",
                aiVideoPrompt = "A photorealistic low angle tracking shot of a security guard walking cautiously down a dark abandoned hospital hallway at 3 AM. His tactical flashlight beam cuts through floating dust motes. Flickering greenish overhead tube light in the distance. Cinematic Fincher-style horror atmosphere, slow smooth forward tracking camera.",
                avoidList = "Fast cuts, bright lighting, goofy monsters, blur, low resolution"
            ),
            DirectorShot(
                shotId = "SHOT_02",
                sceneId = "Scene 01",
                timeCode = "00:07 - 00:15",
                durationSec = 8,
                shotType = "Medium Close-Up (Flashlight Key)",
                cameraAngle = "Dutch Angle (12-degree tilt)",
                cameraMovement = "Handheld Shiver (Reflecting trembling hands)",
                lensSuggestion = "50mm Prime f/1.4",
                composition = "Vikram's face illuminated by bounce light from flashlight hitting locked metal door",
                charactersPresent = "Vikram",
                characterPosition = "Standing 2 feet before door 1304",
                facialExpression = "Sweat running down temple, eyes wide with hyper-vigilance, nostrils flaring",
                bodyLanguage = "Brings walkie-talkie to mouth with shaking left hand",
                dialogue = "VIKRAM: कंट्रोल रूम, फ्लोर 13 पर कोई है... दरवाजा अंदर से बंद है।",
                voiceDirection = "Strained, trembling whisper, voice breaking on 'कंट्रोल'",
                lighting = "Hard specular flashlight bounce creating deep chiaroscuro eye socket shadows",
                background = "Rusted door 1304 with scratch marks near lock",
                colorGrade = "Cold cyan and shadow black",
                soundDesign = "Loud static burst from walkie-talkie, then sudden deafening silence as knocking halts",
                transition = "Whip snap to Shot 03",
                continuityNotes = "Sweat droplet halfway down cheek; walkie-talkie antenna angled left",
                aiVideoPrompt = "A realistic Dutch-angle close-up of a 42-year-old security guard holding a walkie-talkie near his mouth in total darkness. His face is lit by the harsh bounce of his flashlight. Beads of sweat glisten on his forehead. He whispers in authentic Hindi lip synchronization with genuine visceral terror in his eyes. Masterclass horror cinematography.",
                avoidList = "Smile, wooden face, robotic speech, oversaturated colors"
            ),
            DirectorShot(
                shotId = "SHOT_03",
                sceneId = "Scene 01",
                timeCode = "00:15 - 00:23",
                durationSec = 8,
                shotType = "Extreme Close-Up (Pupil Dilation)",
                cameraAngle = "Eye-Level Macro",
                cameraMovement = "Static Locked-Off",
                lensSuggestion = "100mm Macro f/2.8",
                composition = "Extreme close-up on Vikram's right eye and eyebrow",
                charactersPresent = "Vikram",
                characterPosition = "Frozen in terror as knocking stops",
                facialExpression = "Pupil visibly dilating in shock; eyelid stops blinking for 3 seconds",
                bodyLanguage = "Entire breathing halts; veins on temple pulse slightly",
                dialogue = "[Absolute Silence for 4 seconds... then THUD... THUD... THUD... from above]",
                voiceDirection = "Muted breath",
                lighting = "Subtle reflection of flashlight halo in his dark iris",
                background = "Pure inky black",
                colorGrade = "Desaturated monochrome with green tint",
                soundDesign = "Sudden high-frequency ear ringing, then three heavy wooden knocks resounding directly overhead",
                transition = "Instant cut on third knock to Shot 04",
                continuityNotes = "Eye position matches Shot 02 eye line exactly",
                aiVideoPrompt = "Extreme close-up of a terrified man's dark eye in a dark thriller. The pupil dilates in slow motion as complete silence falls. Sweat drips beside his eyebrow. High cinematic fidelity, skin pores and iris fibers sharply in focus, suspenseful psychological acting realism.",
                avoidList = "CGI eye distortion, morphing eyeball, motion blur, bad lighting"
            ),
            DirectorShot(
                shotId = "SHOT_04",
                sceneId = "Scene 01",
                timeCode = "00:23 - 00:30",
                durationSec = 7,
                shotType = "Low Angle Vertigo Tilt-Up",
                cameraAngle = "Extreme Low Angle Looking Straight Up",
                cameraMovement = "Slow 90-degree Tilt-Upward following Flashlight Beam",
                lensSuggestion = "28mm Wide Angle f/2.0",
                composition = "Vikram's terrified profile in lower corner, flashlight beam pointing at waterlogged ceiling tiles",
                charactersPresent = "Vikram",
                characterPosition = "Slowly tilting head backward to look at the ceiling above",
                facialExpression = "Jaw slightly dropped in sheer petrification",
                bodyLanguage = "Flashlight beam wavering as his hands vibrate with adrenaline",
                dialogue = "[Heavy ragged inhalation through nose]",
                voiceDirection = "Hyperventilating breath",
                lighting = "Flashlight circle illuminating a stained ceiling tile that has begun to lift 1 inch",
                background = "Hospital ceiling with rusted ventilation pipe",
                colorGrade = "Deep industrial green and shadow black",
                soundDesign = "Low sub-bass rumble (30Hz), creak of dry gypsum plaster, sudden blackout stinger",
                transition = "Hard smash cut to black",
                continuityNotes = "Flashlight beam firmly centered on middle ceiling seam",
                aiVideoPrompt = "A cinematic low angle shot inside a spooky abandoned corridor. A frightened guard slowly aims his trembling flashlight straight up toward the decaying ceiling tiles. The flashlight beam illuminates a water-damaged ceiling tile shifting slightly upward. Dramatic chiaroscuro lighting, eerie atmospheric dread, masterclass psychological thriller ending.",
                avoidList = "Cartoon jump scare, blurry ceiling, unnatural light source"
            )
        ),
        dialogueReactionSteps = listOf(
            DialogueSequenceStep("Step 1: The Creeping Sound", "Knocks sound at regular 1-second intervals from down the hall.", "Testing human curiosity vs survival instinct.", "Low tracking shot"),
            DialogueSequenceStep("Step 2: Reaching the Barrier", "Vikram stops before the numbered door, confirming sound source.", "Attempting protocol to regain control.", "Medium close-up"),
            DialogueSequenceStep("Step 3: The Cessation Trap", "Sound cuts off the instant Vikram speaks.", "The realization that the entity is listening to him.", "Extreme eye macro"),
            DialogueSequenceStep("Step 4: The Spatial Shift", "Sound returns from an impossible angle: 8 feet above his head.", "Total violation of spatial safety.", "Vertical tilt-up")
        ),
        editingSoundPlan = EditingSoundPlan(
            rhythmPacing = "Prolonged agonizing anticipation; cut lengths increase right before the sudden reveal.",
            dialogueSyncRule = "Walkie-talkie whisper must be clipped and breathy with slight radio squeal.",
            ambientSound = "Low air draft in empty shafts, intermittent buzzing transformer, dead silence.",
            foleyProps = "Heavy boot squeak, metallic click of Maglite switch, radio squelch.",
            musicalScoreCues = listOf(
                "00:00 - Low droning sub-bass sine wave",
                "00:15 - Audio drop-out to 0dB dead room tone",
                "00:23 - Three acoustic wooden thuds with deep cathedral reverb"
            ),
            criticalSilenceMoment = "00:16 to 00:20: Absolute four-second vacuum where even ambient hiss is attenuated.",
            transitionPacing = "Smash cut to black on the final reverberation."
        ),
        qualityChecklist = listOf(
            QualityCheckItem("Acting", "Sweat and breathing rate escalate authentically", true, "Accurate physiological response"),
            QualityCheckItem("Cinematography", "Flashlight beam acts as narrative guide and sole key light", true, "High contrast noir established"),
            QualityCheckItem("Continuity", "Floor 13 corridor props and door 1304 markings consistent", true, "No architectural shifting"),
            QualityCheckItem("Story", "Payoff subverts expectations by moving danger overhead", true, "Spine-chilling climax")
        ),
        fullMasterExport = "UNIVERSAL AI FILM DIRECTOR PRODUCTION PACKAGE\nTitle: Floor 13: The Ceiling Knock\nFormat: 16:9 Landscape (30 Seconds)\nStyle: Fincher Noir Horror\n..."
    )

    val allPresets = listOf(mumbaiMonsoonEncounter, horrorCorridorKnock)
}
