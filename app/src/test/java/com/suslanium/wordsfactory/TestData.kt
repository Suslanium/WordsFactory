package com.suslanium.wordsfactory

import com.suslanium.wordsfactory.domain.entity.dictionary.Definition
import com.suslanium.wordsfactory.domain.entity.dictionary.Meaning
import com.suslanium.wordsfactory.domain.entity.dictionary.WordEtymology
import com.suslanium.wordsfactory.domain.entity.dictionary.WordInfo
import com.suslanium.wordsfactory.domain.entity.dictionary.WordWithCoefficient

object TestData {

    val otherWords = listOf("build", "destroy", "test")

    val coefficientTestWord = WordWithCoefficient(
        WordInfo(
            isAdded = true, etymologies = listOf(
                WordEtymology(
                    word = "cooking",
                    phonetic = "/ˈkʊ.kɪŋ/",
                    audioUrl = null,
                    meanings = listOf(
                        Meaning(
                            partOfSpeech = "verb",
                            definitions = listOf(
                                Definition(
                                    definition = "To prepare (food) for eating by heating it, often by combining it with other ingredients.",
                                    example = "I'm cooking bangers and mash."
                                ), Definition(
                                    definition = "To prepare (unspecified) food for eating by heating it, often by combining it with other ingredients.",
                                    example = "He's in the kitchen, cooking."
                                ), Definition(
                                    definition = "To be cooked.",
                                    example = "The dinner is cooking on the stove."
                                ), Definition(
                                    definition = "To be uncomfortably hot.",
                                    example = "Look at that poor dog shut up in that car on a day like today - it must be cooking in there."
                                ), Definition(
                                    definition = "To execute by electric chair.", example = null
                                ), Definition(
                                    definition = "To hold onto (a grenade) briefly after igniting the fuse, so that it explodes almost immediately after being thrown.",
                                    example = null
                                ), Definition(
                                    definition = "To concoct or prepare.", example = null
                                ), Definition(
                                    definition = "To tamper with or alter; to cook up.",
                                    example = null
                                ), Definition(
                                    definition = "To play or improvise in an inspired and rhythmically exciting way. (From 1930s jive talk.)",
                                    example = "Crank up the Coltrane and start cooking!"
                                ), Definition(
                                    definition = "To play music vigorously.",
                                    example = "On the Wagner piece, the orchestra was cooking!"
                                )
                            ),
                        ), Meaning(
                            partOfSpeech = "verb", definitions = listOf(
                                Definition(
                                    definition = "To make the noise of the cuckoo.",
                                    example = null
                                )
                            )
                        ), Meaning(
                            partOfSpeech = "verb",
                            definitions = listOf(
                                Definition(
                                    definition = "To throw.", example = null
                                )
                            ),
                        ), Meaning(
                            partOfSpeech = "noun",
                            definitions = listOf(
                                Definition(
                                    definition = "The process of preparing food by using heat.",
                                    example = null
                                ), Definition(
                                    definition = "An instance of preparing food by using heat.",
                                    example = null
                                ), Definition(
                                    definition = "The result of preparing food by using heat.",
                                    example = null
                                )
                            ),
                        ), Meaning(
                            partOfSpeech = "adjective",
                            definitions = listOf(
                                Definition(
                                    definition = "In progress, happening.",
                                    example = "The project took a few days to gain momentum, but by the end of the week, things were really cooking."
                                )
                            ),
                        )
                    )
                )
            )
        ), coefficient = 0
    )

    val wordsWithCoefficients = listOf(
        WordWithCoefficient(
            WordInfo(
                isAdded = true, etymologies = listOf(
                    WordEtymology(
                        word = "cooking",
                        phonetic = "/ˈkʊ.kɪŋ/",
                        audioUrl = null,
                        meanings = listOf(
                            Meaning(
                                partOfSpeech = "verb",
                                definitions = listOf(
                                    Definition(
                                        definition = "To prepare (food) for eating by heating it, often by combining it with other ingredients.",
                                        example = "I'm cooking bangers and mash."
                                    ), Definition(
                                        definition = "To prepare (unspecified) food for eating by heating it, often by combining it with other ingredients.",
                                        example = "He's in the kitchen, cooking."
                                    ), Definition(
                                        definition = "To be cooked.",
                                        example = "The dinner is cooking on the stove."
                                    ), Definition(
                                        definition = "To be uncomfortably hot.",
                                        example = "Look at that poor dog shut up in that car on a day like today - it must be cooking in there."
                                    ), Definition(
                                        definition = "To execute by electric chair.", example = null
                                    ), Definition(
                                        definition = "To hold onto (a grenade) briefly after igniting the fuse, so that it explodes almost immediately after being thrown.",
                                        example = null
                                    ), Definition(
                                        definition = "To concoct or prepare.", example = null
                                    ), Definition(
                                        definition = "To tamper with or alter; to cook up.",
                                        example = null
                                    ), Definition(
                                        definition = "To play or improvise in an inspired and rhythmically exciting way. (From 1930s jive talk.)",
                                        example = "Crank up the Coltrane and start cooking!"
                                    ), Definition(
                                        definition = "To play music vigorously.",
                                        example = "On the Wagner piece, the orchestra was cooking!"
                                    )
                                ),
                            ), Meaning(
                                partOfSpeech = "verb", definitions = listOf(
                                    Definition(
                                        definition = "To make the noise of the cuckoo.",
                                        example = null
                                    )
                                )
                            ), Meaning(
                                partOfSpeech = "verb",
                                definitions = listOf(
                                    Definition(
                                        definition = "To throw.", example = null
                                    )
                                ),
                            ), Meaning(
                                partOfSpeech = "noun",
                                definitions = listOf(
                                    Definition(
                                        definition = "The process of preparing food by using heat.",
                                        example = null
                                    ), Definition(
                                        definition = "An instance of preparing food by using heat.",
                                        example = null
                                    ), Definition(
                                        definition = "The result of preparing food by using heat.",
                                        example = null
                                    )
                                ),
                            ), Meaning(
                                partOfSpeech = "adjective",
                                definitions = listOf(
                                    Definition(
                                        definition = "In progress, happening.",
                                        example = "The project took a few days to gain momentum, but by the end of the week, things were really cooking."
                                    )
                                ),
                            )
                        )
                    )
                )
            ), coefficient = 0
        ), WordWithCoefficient(
            WordInfo(
                isAdded = true, etymologies = listOf(
                    WordEtymology(
                        word = "freezing",
                        phonetic = "/ˈfɹiːz.ɪŋ/",
                        audioUrl = null,
                        meanings = listOf(
                            Meaning(
                                partOfSpeech = "verb",
                                definitions = listOf(
                                    Definition(
                                        definition = "Especially of a liquid, to become solid due to low temperature.",
                                        example = "The lake froze solid."
                                    ), Definition(
                                        definition = "To lower something's temperature to the point that it freezes or becomes hard.",
                                        example = "Don't freeze meat twice."
                                    ), Definition(
                                        definition = "To drop to a temperature below zero degrees celsius, where water turns to ice.",
                                        example = "It didn't freeze this winter, but last winter was very harsh."
                                    ), Definition(
                                        definition = "To be affected by extreme cold.",
                                        example = "Don't go outside wearing just a t-shirt; you'll freeze!"
                                    ), Definition(
                                        definition = "(of machines and software) To come to a sudden halt, stop working (functioning).",
                                        example = "Since the last update, the program freezes / freezes up after a few minutes of use."
                                    ), Definition(
                                        definition = "(of people and other animals) To stop (become motionless) or be stopped due to attentiveness, fear, surprise, etc.",
                                        example = "Despite all of the rehearsals, I froze up as soon as I got on stage."
                                    ), Definition(
                                        definition = "To cause someone to become motionless.",
                                        example = null
                                    ), Definition(
                                        definition = "To lose or cause to lose warmth of feeling; to shut out; to ostracize.",
                                        example = "Over time, he froze towards her, and ceased to react to her friendly advances."
                                    ), Definition(
                                        definition = "To cause loss of animation or life in, from lack of heat; to give the sensation of cold to; to chill.",
                                        example = null
                                    ), Definition(
                                        definition = "To prevent the movement or liquidation of a person's financial assets",
                                        example = "The court froze the criminal's bank account."
                                    ), Definition(
                                        definition = "Of prices, spending etc., to keep at the same level, without any increase.",
                                        example = null
                                    )
                                ),
                            ), Meaning(
                                partOfSpeech = "noun",
                                definitions = listOf(
                                    Definition(
                                        definition = "The change in state of a substance from liquid to solid by cooling to a critically low temperature.",
                                        example = null
                                    ), Definition(
                                        definition = "The action of numbing with anesthetics.",
                                        example = null
                                    )
                                ),
                            ), Meaning(
                                partOfSpeech = "adjective",
                                definitions = listOf(
                                    Definition(
                                        definition = "Suffering or causing frost", example = null
                                    ), Definition(
                                        definition = "(by extension) Very cold", example = null
                                    )
                                ),
                            )
                        )
                    )
                )
            ), coefficient = 1
        ), WordWithCoefficient(
            WordInfo(
                isAdded = true, etymologies = listOf(
                    WordEtymology(
                        word = "smiling",
                        phonetic = "/ˈsmaɪlɪŋ/",
                        audioUrl = "https://api.dictionaryapi.dev/media/pronunciations/en/smiling-us.mp3",
                        meanings = listOf(
                            Meaning(
                                partOfSpeech = "verb",
                                definitions = listOf(
                                    Definition(
                                        definition = "To have (a smile) on one's face.",
                                        example = "I don't know what he's smiling about."
                                    ), Definition(
                                        definition = "To express by smiling.",
                                        example = "to smile consent, or a welcome"
                                    ), Definition(
                                        definition = "To express amusement, pleasure, or love and kindness.",
                                        example = null
                                    ), Definition(
                                        definition = "To look cheerful and joyous; to have an appearance suited to excite joy.",
                                        example = "The sun smiled down from a clear summer sky."
                                    ), Definition(
                                        definition = "To be propitious or favourable; to countenance.",
                                        example = "The gods smiled on his labours."
                                    )
                                ),
                            ), Meaning(
                                partOfSpeech = "noun",
                                definitions = listOf(
                                    Definition(
                                        definition = "The action of the verb to smile.",
                                        example = "Smiling takes fewer muscles than frowning."
                                    )
                                ),
                            ), Meaning(
                                partOfSpeech = "adjective",
                                definitions = listOf(
                                    Definition(
                                        definition = "That smiles or has a smile.",
                                        example = "smiling children"
                                    )
                                ),
                            )
                        )
                    )
                )
            ), coefficient = 2
        )
    )

}