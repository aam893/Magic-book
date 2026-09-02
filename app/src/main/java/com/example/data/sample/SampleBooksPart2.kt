package com.example.data.sample

import com.example.data.model.BilingualParagraph
import com.example.data.model.Book
import com.example.data.model.BookCoverTheme
import com.example.data.model.DifficultyLevel
import com.example.data.model.Language

object SampleBooksPart2 {
    val list: List<Book> = listOf(
        // ==================== 4. ARABIC (العربية) - 15 Stories ====================
        Book(
            id = "ar-01-sindbad",
            title = "حكاية السندباد البحري",
            translatedTitle = "The Tale of Sinbad the Sailor",
            author = "ألف ليلة وليلة",
            description = "The classic voyage of Sinbad encountering fantastical creatures and uncharted islands.",
            targetLanguage = Language.ARABIC,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "⛵",
            coverGradientStart = 0xFF78350F,
            coverGradientEnd = 0xFFB45309,
            coverTheme = BookCoverTheme.DESERT_AMBER,
            totalWords = 290,
            explicitPageCount = 3,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "كان في قديم الزمان بمدينة بغداد رجل تاجر يُدعى السندباد البحري، وكان ذا مال جزيل وخيرات كثيرة.",
                    translationText = "In ancient times in the city of Baghdad, there was a merchant named Sinbad the Sailor, who possessed great wealth."
                ),
                BilingualParagraph(
                    targetText = "قال السندباد: اعلموا يا إخواني أنني ركبت البحر في سبع سفرات، وكل سفرة كانت أعجب من أختها في الأهوال والمغامرات.",
                    translationText = "Sinbad said: Know, my brothers, that I sailed the sea on seven voyages, and each was more wondrous than the other in perils."
                ),
                BilingualParagraph(
                    targetText = "وصلنا إلى جزيرة خضراء، ولكن الأرض تحركت فجأة! لم تكن جزيرة، بل كانت حوتاً عملاقاً نائماً في قاع البحر!",
                    translationText = "We reached a green island, but suddenly the ground moved! It was not an island, but a giant whale sleeping in the sea!"
                )
            )
        ),
        Book(
            id = "ar-02-alaeddin",
            title = "علاء الدين والمصباح السحري",
            translatedTitle = "Aladdin and the Magic Lamp",
            author = "حكايات التراث العربي",
            description = "A young courageous youth discovers a mystical enchanted oil lamp containing a mighty Genie.",
            targetLanguage = Language.ARABIC,
            difficulty = DifficultyLevel.A2,
            coverEmoji = "🪔",
            coverGradientStart = 0xFFD4AF37,
            coverGradientEnd = 0xFF78350F,
            coverTheme = BookCoverTheme.DESERT_AMBER,
            totalWords = 310,
            explicitPageCount = 3,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "عاش علاء الدين مع أمه في قرية صغيرة وكان فتى طيب القلب محباً للمغامرة والبحث عن العجائب.",
                    translationText = "Aladdin lived with his mother in a small village and was a kind-hearted youth who loved adventure."
                ),
                BilingualParagraph(
                    targetText = "مسح المصباح القديم بيده، فانبعث دخان أزرق كثيف وظهر مارد عظيم يقول: شبيك لبيك، خادم المصباح بين يديك!",
                    translationText = "He rubbed the old lamp with his hand, dense blue smoke billowed out and a majestic genie said: At your command, the servant of the lamp is here!"
                )
            )
        ),
        Book(
            id = "ar-03-ibn-battuta",
            title = "رحلات ابن بطوطة العجيبة",
            translatedTitle = "The Voyages of Ibn Battuta",
            author = "ابن بطوطة (تحفة النظار)",
            description = "The historical 30-year journey of the great Moroccan traveler across the medieval world.",
            targetLanguage = Language.ARABIC,
            difficulty = DifficultyLevel.B2,
            coverEmoji = "🧭",
            coverGradientStart = 0xFF064E3B,
            coverGradientEnd = 0xFF047857,
            coverTheme = BookCoverTheme.NOBLE_EMERALD,
            totalWords = 300,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "خرجتُ من طنجة مسقط رأسي معتمداً حج بيت الله الحرام وزيارة الآفاق والبلدان النائية.",
                    translationText = "I set out from Tangier, my birthplace, intending to perform pilgrimage and explore distant lands and horizons."
                ),
                BilingualParagraph(
                    targetText = "قطعتُ آلاف الأميال عبر الصحاري والبحار، ودوّنتُ عادات الملوك والشعوب في الهند والسند والصين.",
                    translationText = "I traversed thousands of miles across deserts and seas, recording customs of nations in India and China."
                )
            )
        ),
        Book(
            id = "ar-04-yawm-fi-fas",
            title = "يوم في أزقة فاس العتيقة",
            translatedTitle = "A Day in the Ancient Alleys of Fez",
            author = "طارق العلمي",
            description = "A peaceful morning walking through artisan courtyards and historic libraries in Morocco.",
            targetLanguage = Language.ARABIC,
            difficulty = DifficultyLevel.A1,
            coverEmoji = "🕌",
            coverGradientStart = 0xFF0F172A,
            coverGradientEnd = 0xFF312E81,
            coverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
            totalWords = 220,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "استيقظت المدينة القديمة على صوت الأذان وصوت خرير الماء في النوافير المزخرفة بالفسيفساء المغربية.",
                    translationText = "The ancient Medina woke to the call to prayer and the murmur of water in Moroccan mosaic fountains."
                ),
                BilingualParagraph(
                    targetText = "في سوق العطارين، تفوح روائح الزعفران والقرفة والورد العطر بينما الحرفيون يصنعون أواني النحاس اللامعة.",
                    translationText = "In the spice market, scents of saffron and cinnamon fill the air while artisans shape shining copper vessels."
                )
            )
        ),
        Book(
            id = "ar-05-kalila-wa-dimna",
            title = "كليلة ودمنة: الأسد والأرنب الذكي",
            translatedTitle = "Kalila and Dimna: The Lion and the Clever Hare",
            author = "ابن المقفع",
            description = "The timeless philosophical fable showing that intelligence and wisdom overcome brute force.",
            targetLanguage = Language.ARABIC,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "🦁",
            coverGradientStart = 0xFF991B1B,
            coverGradientEnd = 0xFFD97706,
            coverTheme = BookCoverTheme.CRIMSON_RUBY,
            totalWords = 280,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "كان في أرض خصبة أسد جبار يخيف حيوانات الغابة، فاتفقت معه على أن ترسل له طعاماً كل يوم في موعده.",
                    translationText = "In a fertile land there was a mighty lion that terrified the forest animals, so they agreed to send him food daily."
                ),
                BilingualParagraph(
                    targetText = "فلما جاء دور الأرنب الصغير، احتال بحكمة وقاد الأسد إلى بئر عميقة ليرى انعكاس صورته فيظنه غريماً له.",
                    translationText = "When the little hare's turn arrived, he cleverly led the lion to a deep well to see his reflection and think it a rival."
                )
            )
        ),
        Book(
            id = "ar-06-al-mutanabbi",
            title = "حكمة المتنبي ورحلة الشعر",
            translatedTitle = "The Wisdom of Al-Mutanabbi",
            author = "أبو الطيب المتنبي",
            description = "Reflections on ambition, honor, and courage from the greatest classical poet of the Arabic language.",
            targetLanguage = Language.ARABIC,
            difficulty = DifficultyLevel.C1,
            coverEmoji = "📜",
            coverGradientStart = 0xFF3E2723,
            coverGradientEnd = 0xFF78350F,
            coverTheme = BookCoverTheme.VINTAGE_LEATHER,
            totalWords = 290,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "عَلَى قَدْرِ أَهْلِ العَزْمِ تَأْتِي العَزائِمُ، وَتَأْتِي عَلَى قَدْرِ الكِرَامِ المَكَارِمُ.",
                    translationText = "In proportion to people of determination come determinations, and in proportion to the noble come noble deeds."
                ),
                BilingualParagraph(
                    targetText = "وَتَعْظُمُ فِي عَيْنِ الصَّغِيرِ صِغَارُهَا، وَتَصْغُرُ فِي عَيْنِ العَظِيمِ العَظَائِمُ.",
                    translationText = "Small things loom large in the eyes of the small, while great feats seem simple to the truly great."
                )
            )
        ),
        Book(
            id = "ar-07-marrakech-jemaa",
            title = "أمسية في ساحة جامع الفنا",
            translatedTitle = "An Evening in Jemaa el-Fnaa",
            author = "يوسف المرابط",
            description = "Storytellers, musicians, and orange juice stalls under the starlit sky of Marrakech.",
            targetLanguage = Language.ARABIC,
            difficulty = DifficultyLevel.A2,
            coverEmoji = "🥁",
            coverGradientStart = 0xFFB45309,
            coverGradientEnd = 0xFFDC2626,
            coverTheme = BookCoverTheme.DESERT_AMBER,
            totalWords = 250,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "مع غروب الشمس وراء صومعة الكتبية، تتحول ساحة جامع الفنا إلى مسرح مفتوح يفيض بالألوان والأنغام.",
                    translationText = "As the sun sets behind the Koutoubia minaret, Jemaa el-Fnaa square transforms into an open stage of colors and rhythms."
                ),
                BilingualParagraph(
                    targetText = "يلتف الناس حول الحكواتي وهو يروي مغامرات عنترة بن شداد بشغف وحماسة تأسر القلوب.",
                    translationText = "People gather around the storyteller as he passionately recounts the epic adventures of Antarah ibn Shaddad."
                )
            )
        ),
        Book(
            id = "ar-08-taj-mahal-andalus",
            title = "قصر الحمراء وجنة العريف",
            translatedTitle = "The Alhambra and Generalife",
            author = "سلمى بنجلون",
            description = "Marble columns, flowing channels of water, and poetic Arabic calligraphy in Granada.",
            targetLanguage = Language.ARABIC,
            difficulty = DifficultyLevel.B2,
            coverEmoji = "🏛️",
            coverGradientStart = 0xFF065F46,
            coverGradientEnd = 0xFF047857,
            coverTheme = BookCoverTheme.NOBLE_EMERALD,
            totalWords = 280,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "يقف قصر الحمراء في غرناطة كتحفة هندسية فريدة، حيث تمتزج أشعة الشمس مع أقواس الجص المنحوتة بإتقان.",
                    translationText = "The Alhambra in Granada stands as a unique architectural masterpiece, where sunlight blends with intricately carved stucco arches."
                ),
                BilingualParagraph(
                    targetText = "نقش المعماريون على جدرانه عبارة 'ولا غالب إلا الله' لتبقى شاهداً أبدياً على روح الفن الأندلسي الأصيل.",
                    translationText = "Architects inscribed 'There is no victor but God' across its walls, a timeless testament to authentic Andalusian art."
                )
            )
        ),
        Book(
            id = "ar-09-nile-cairo",
            title = "نسيم النيل في القاهرة",
            translatedTitle = "Nile Breeze in Cairo",
            author = "كريم سامي",
            description = "Feluccas gliding past ancient date palms and vibrant riverbanks at dusk.",
            targetLanguage = Language.ARABIC,
            difficulty = DifficultyLevel.A1,
            coverEmoji = "⛵",
            coverGradientStart = 0xFF0284C7,
            coverGradientEnd = 0xFF0F172A,
            coverTheme = BookCoverTheme.CELESTIAL_INDIGO,
            totalWords = 230,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "ركبتُ قارباً شراعياً في نهر النيل الخالد بينما كانت نسائم المساء تداعب مياه النهر الهادئة.",
                    translationText = "I boarded a sailing boat on the immortal Nile River while evening breezes caressed the calm waters."
                ),
                BilingualParagraph(
                    targetText = "أضواء القاهرة تتلألأ على الضفتين، معلنة بداية ليلة جديدة مليئة بالفرح والموسيقى الشرقية.",
                    translationText = "The lights of Cairo twinkle on both banks, heralding the start of a new night filled with joy and oriental music."
                )
            )
        ),
        Book(
            id = "ar-10-al-farabi",
            title = "الفارابي والمدينة الفاضلة",
            translatedTitle = "Al-Farabi and the Virtuous City",
            author = "أبو نصر الفارابي",
            description = "Philosophical exploration of harmonious society governed by justice, knowledge, and mutual cooperation.",
            targetLanguage = Language.ARABIC,
            difficulty = DifficultyLevel.C1,
            coverEmoji = "🏛️",
            coverGradientStart = 0xFF312E81,
            coverGradientEnd = 0xFF1E1B4B,
            coverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
            totalWords = 310,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "إن المدينة الفاضلة تشبه الجسد التام الصحيح الذي تتعاون أعضاؤه كلها على إتمام الحياة وحفظها.",
                    translationText = "The virtuous city resembles a complete, healthy body whose parts all cooperate to maintain and protect life."
                ),
                BilingualParagraph(
                    targetText = "فإذا سعى كل فرد في المجتمع نحو الخير والفضيلة، تحققت السعادة الحقيقية للجميع في أبهى صورها.",
                    translationText = "When every individual in society strives toward goodness and virtue, true happiness is achieved for all in its fullest form."
                )
            )
        ),
        Book(
            id = "ar-11-qahwa-arabiya",
            title = "أسرار القهوة العربية الأصيلة",
            translatedTitle = "Secrets of Authentic Arabic Coffee",
            author = "حمد القحطاني",
            description = "Traditions of hospitality, roasted cardamom beans, and golden dallah pots in Arabian tents.",
            targetLanguage = Language.ARABIC,
            difficulty = DifficultyLevel.A2,
            coverEmoji = "☕",
            coverGradientStart = 0xFF78350F,
            coverGradientEnd = 0xFFD4AF37,
            coverTheme = BookCoverTheme.DESERT_AMBER,
            totalWords = 240,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "تعتبر القهوة برائحة الهيل والزعفران رمزاً عريقاً للكرم والضيافة في كل مجلس عربي أصيل.",
                    translationText = "Coffee brewed with cardamom and saffron is a venerable symbol of generosity and hospitality in every authentic Arabic gathering."
                ),
                BilingualParagraph(
                    targetText = "يُصب الفنجان باليد اليسرى ويُقدم باليد اليمنى كتحية احترام وتقدير للضيف العزيز.",
                    translationText = "The cup is poured with the left hand and presented with the right hand as a sign of respect and honor for the esteemed guest."
                )
            )
        ),
        Book(
            id = "ar-12-al-zahrawi",
            title = "الزهراوي: رائد الجراحة والطب",
            translatedTitle = "Al-Zahrawi: Pioneer of Surgery",
            author = "د. فاطمة الشريف",
            description = "The Andalusia scientist who invented modern surgical instruments in Cordoba over a millennium ago.",
            targetLanguage = Language.ARABIC,
            difficulty = DifficultyLevel.B2,
            coverEmoji = "⚕️",
            coverGradientStart = 0xFF065F46,
            coverGradientEnd = 0xFF047857,
            coverTheme = BookCoverTheme.NOBLE_EMERALD,
            totalWords = 270,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "ابتكر الطبيب الأندلسي أبو القاسم الزهراوي أكثر من مائتي أداة جراحية غيّرت مسار الطب الإنساني.",
                    translationText = "The Andalusian physician Abu al-Qasim al-Zahrawi invented over two hundred surgical instruments that revolutionized human medicine."
                ),
                BilingualParagraph(
                    targetText = "كان كتابه 'التصريف لمن عجز عن التأليف' مرجعاً طبياً أساسياً في جامعات أوروبا لقرون طويلة.",
                    translationText = "His treatise 'Kitab al-Tasrif' was a core medical reference in European universities for centuries."
                )
            )
        ),
        Book(
            id = "ar-13-sahara-nojoom",
            title = "ليلة تحت نجوم الصحراء الكبرى",
            translatedTitle = "A Night Under the Sahara Stars",
            author = "محمود التارقي",
            description = "Campfires, camel caravans, and endless celestial constellations in the Sahara dunes.",
            targetLanguage = Language.ARABIC,
            difficulty = DifficultyLevel.A1,
            coverEmoji = "🐪",
            coverGradientStart = 0xFFB45309,
            coverGradientEnd = 0xFF78350F,
            coverTheme = BookCoverTheme.DESERT_AMBER,
            totalWords = 210,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "في قلب الكثبان الرملية الذهبية، يوقد الطوارق نار الحطب لصنع الشاي الصحراوي المنعش.",
                    translationText = "In the heart of golden sand dunes, the Tuareg light a campfire to brew refreshing desert tea."
                ),
                BilingualParagraph(
                    targetText = "السماء صافية ومليئة بآلاف النجوم اللامعة التي ترشد المسافرين عبر الرمال الشاسعة.",
                    translationText = "The sky is clear and filled with thousands of gleaming stars guiding travelers across vast sands."
                )
            )
        ),
        Book(
            id = "ar-14-hayy-ibn-yaqzan",
            title = "حي بن يقظان والجزيرة المهجورة",
            translatedTitle = "Hayy ibn Yaqzan and the Desert Island",
            author = "ابن طفيل",
            description = "A boy raised by a deer on an uninhabited island discovers truth, astronomy, and philosophy through pure reason.",
            targetLanguage = Language.ARABIC,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "🦌",
            coverGradientStart = 0xFF047857,
            coverGradientEnd = 0xFF064E3B,
            coverTheme = BookCoverTheme.NOBLE_EMERALD,
            totalWords = 290,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "نشأ الطفل 'حي' في جزيرة استوائية منعزلة وتأمل في حركة الكواكب والنجوم وطبائع الكائنات الحية.",
                    translationText = "The child 'Hayy' grew up on an isolated tropical island and pondered the movement of planets, stars, and living creatures."
                ),
                BilingualParagraph(
                    targetText = "تدرج في معرفة العالم بعقله وتأمله العميق حتى أدرك أن للكون خالقاً حكيماً قادراً على كل شيء.",
                    translationText = "He progressed in understanding the universe through reason and deep contemplation until realizing the cosmos has a wise, all-powerful Creator."
                )
            )
        ),
        Book(
            id = "ar-15-andalus-gharnata",
            title = "زمن الورود في غرناطة",
            translatedTitle = "Time of Roses in Granada",
            author = "ابن زمرك",
            description = "Love, courtyard poetry, and blooming jasmines by the snowy Sierra Nevada peaks.",
            targetLanguage = Language.ARABIC,
            difficulty = DifficultyLevel.B2,
            coverEmoji = "🌹",
            coverGradientStart = 0xFF991B1B,
            coverGradientEnd = 0xFFBE123C,
            coverTheme = BookCoverTheme.CRIMSON_RUBY,
            totalWords = 260,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "تتفتح أزهار الياسمين في رياض البيازين فتعبق الأمسيات بنسيم رقيق ينعش الوجدان والخواطر.",
                    translationText = "Jasmine blossoms bloom in Albayzín gardens, filling evenings with a gentle breeze that refreshes spirit and mind."
                ),
                BilingualParagraph(
                    targetText = "يجتمع الشعراء لينشدوا على أوتار العود قصائد تروي حكاية حب أندلسي خالد لا تمحوه الأيام.",
                    translationText = "Poets gather to recite poems to the strings of the oud, telling a timeless Andalusian love story unmarred by days."
                )
            )
        ),

        // ==================== 5. JAPANESE (日本語) - 10 Stories ====================
        Book(
            id = "ja-01-momotaro",
            title = "桃太郎 (Momotarō)",
            translatedTitle = "The Peach Boy",
            author = "Traditional Folklore",
            description = "A brave boy born from a peach journeys to Ogre Island with animal companions.",
            targetLanguage = Language.JAPANESE,
            difficulty = DifficultyLevel.A1,
            coverEmoji = "🍑",
            coverGradientStart = 0xFF7F1D1D,
            coverGradientEnd = 0xFF991B1B,
            coverTheme = BookCoverTheme.CRIMSON_RUBY,
            totalWords = 240,
            explicitPageCount = 3,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "むかし、むかし、あるところに、おじいさんとおばあさんが住んでいました。",
                    translationText = "Long, long ago, in a certain place, there lived an old man and an old woman."
                ),
                BilingualParagraph(
                    targetText = "おばあさんが川で洗濯をしていると、川上から大きな桃が流れてきました。",
                    translationText = "While the old woman was washing clothes in the river, a large peach came floating down from upstream."
                ),
                BilingualParagraph(
                    targetText = "桃を切ると、中から元気な男の子が飛び出してきました！",
                    translationText = "When they cut the peach open, a healthy, energetic boy popped out from inside!"
                )
            )
        ),
        Book(
            id = "ja-02-kaguya-hime",
            title = "竹取物語 (かぐや姫)",
            translatedTitle = "The Tale of the Bamboo Cutter",
            author = "Classical Folklore",
            description = "A bamboo cutter finds a celestial miniature child inside a glowing bamboo stalk.",
            targetLanguage = Language.JAPANESE,
            difficulty = DifficultyLevel.A2,
            coverEmoji = "🎋",
            coverGradientStart = 0xFF064E3B,
            coverGradientEnd = 0xFF047857,
            coverTheme = BookCoverTheme.NOBLE_EMERALD,
            totalWords = 280,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "竹林の中で根元が黄金に光り輝く不思議な竹を見つけました。中には美しい少女が座っていました。",
                    translationText = "In the bamboo grove he found a mysterious bamboo glowing golden at the base. Inside sat a beautiful tiny girl."
                ),
                BilingualParagraph(
                    targetText = "少女は成長するにつれ、類まれな美しさを持つかぐや姫となりました。",
                    translationText = "As she grew, she became Princess Kaguya of incomparable celestial beauty."
                )
            )
        ),
        Book(
            id = "ja-03-wagahai-neko",
            title = "吾輩は猫である",
            translatedTitle = "I Am a Cat",
            author = "夏目漱石 (Natsume Sōseki)",
            description = "Observing human foolishness through the witty philosophical lens of an unnamed cat.",
            targetLanguage = Language.JAPANESE,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "🐱",
            coverGradientStart = 0xFF3E2723,
            coverGradientEnd = 0xFF4E342E,
            coverTheme = BookCoverTheme.VINTAGE_LEATHER,
            totalWords = 290,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "吾輩は猫である。名前はまだ無い。どこで生れたかとんと見当がつかぬ。",
                    translationText = "I am a cat. As yet I have no name. I have not the slightest idea where I was born."
                ),
                BilingualParagraph(
                    targetText = "人間というのは時々、実に不可思議な行動をするものだと感心するばかりである。",
                    translationText = "Humans occasionally behave in truly mysterious ways, which never ceases to amaze me."
                )
            )
        ),
        Book(
            id = "ja-04-tokyo-sakura",
            title = "東京の夜と桜の道",
            translatedTitle = "Tokyo Night and Cherry Blossom Path",
            author = "田中 健一",
            description = "Illuminated pink cherry blossoms beside Meguro River surrounded by lantern light.",
            targetLanguage = Language.JAPANESE,
            difficulty = DifficultyLevel.A1,
            coverEmoji = "🌸",
            coverGradientStart = 0xFFEC4899,
            coverGradientEnd = 0xFF4F46E5,
            coverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
            totalWords = 230,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "春の夜、目黒川沿いの桜がピンク色のトンネルを作っています。提灯の明かりが川面に美しく映っています。",
                    translationText = "On a spring night, cherry blossoms along Meguro River create a pink tunnel. Lantern lights reflect beautifully on the river."
                ),
                BilingualParagraph(
                    targetText = "温かい抹茶を飲みながら、舞い落ちる花びらを静かに眺める時間はとても平和です。",
                    translationText = "Drinking warm matcha while quietly watching falling petals is wonderfully peaceful."
                )
            )
        ),
        Book(
            id = "ja-05-urashima-taro",
            title = "浦島太郎の竜宮城",
            translatedTitle = "Urashima Taro and the Dragon Palace",
            author = "Traditional Legend",
            description = "A kind fisherman saves a sea turtle and visits the magical undersea Dragon Palace.",
            targetLanguage = Language.JAPANESE,
            difficulty = DifficultyLevel.A2,
            coverEmoji = "🐢",
            coverGradientStart = 0xFF0284C7,
            coverGradientEnd = 0xFF0F172A,
            coverTheme = BookCoverTheme.CELESTIAL_INDIGO,
            totalWords = 270,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "優しい漁師の浦島太郎は、浜辺で子供たちにいじめられていた亀を助けて海へ逃がしてやりました。",
                    translationText = "The kind fisherman Urashima Taro rescued a turtle being teased by children on the beach and released it into the sea."
                ),
                BilingualParagraph(
                    targetText = "亀の背中に乗って海底深くにある竜宮城へ行き、乙姫様から豪華なもてなしを受けました。",
                    translationText = "Riding on the turtle's back to the Dragon Palace deep undersea, he received lavish hospitality from Princess Otohime."
                )
            )
        ),
        Book(
            id = "ja-06-rashomon",
            title = "羅生門の夕暮れ",
            translatedTitle = "Dusk at Rashomon",
            author = "芥川龍之介 (Ryūnosuke Akutagawa)",
            description = "A dramatic exploration of survival and human nature under the decaying southern gate of ancient Kyoto.",
            targetLanguage = Language.JAPANESE,
            difficulty = DifficultyLevel.B2,
            coverEmoji = "⛩️",
            coverGradientStart = 0xFF312E81,
            coverGradientEnd = 0xFF1E1B4B,
            coverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
            totalWords = 300,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "ある日の暮れ方の事である。一人の下人が、羅生門の下で雨やみを待っていた。",
                    translationText = "It was one evening. A lowly servant was waiting beneath the Rashomon gate for the rain to cease."
                ),
                BilingualParagraph(
                    targetText = "門の上の薄暗い楼閣で、彼は生き延びるために必死な老婆の姿を目撃した。",
                    translationText = "In the dim pavilion atop the gate, he witnessed an old woman struggling desperately to survive."
                )
            )
        ),
        Book(
            id = "ja-07-matsuri-kyoto",
            title = "京都の祇園祭",
            translatedTitle = "Kyoto's Gion Festival",
            author = "佐藤 雅子",
            description = "Giant wooden floats, flute melodies, and yukata robes filling Kyoto's ancient streets.",
            targetLanguage = Language.JAPANESE,
            difficulty = DifficultyLevel.A1,
            coverEmoji = "🏮",
            coverGradientStart = 0xFFD97706,
            coverGradientEnd = 0xFFDC2626,
            coverTheme = BookCoverTheme.DESERT_AMBER,
            totalWords = 220,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "夏の京都では、千年の歴史を持つ祇園祭が始まります。通りには大きな山鉾が並びます。",
                    translationText = "In summer Kyoto, the thousand-year-old Gion Festival begins. Giant decorated floats line the streets."
                ),
                BilingualParagraph(
                    targetText = "太鼓と笛の軽快な囃子が響き渡り、浴衣を着た人々が屋台の美味しい食べ物を楽しんでいます。",
                    translationText = "Upbeat festival flute and drum rhythms echo, and people in yukatas enjoy delicious food stall treats."
                )
            )
        ),
        Book(
            id = "ja-08-ginga-tetsudo",
            title = "銀河鉄道の夜",
            translatedTitle = "Night on the Galactic Railroad",
            author = "宮沢賢治 (Kenji Miyazawa)",
            description = "Two young boys board a mystical celestial steam train traversing luminous constellations of the Milky Way.",
            targetLanguage = Language.JAPANESE,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "🌌",
            coverGradientStart = 0xFF1E1B4B,
            coverGradientEnd = 0xFF0F172A,
            coverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
            totalWords = 290,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "ジョバンニが目を開けると、いつの間にかごとごとと走る小さな夜行列車の中に座っていました。",
                    translationText = "When Giovanni opened his eyes, he found himself sitting inside a small night train rattling softly along."
                ),
                BilingualParagraph(
                    targetText = "車窓の外には、天の川の青白いリンドウの花や三角標が美しく輝いていました。",
                    translationText = "Outside the train window, pale blue gentian flowers of the Milky Way and starry signal beacons sparkled brilliantly."
                )
            )
        ),
        Book(
            id = "ja-09-fuji-san",
            title = "霊峰富士の日の出",
            translatedTitle = "Sunrise on Sacred Mount Fuji",
            author = "山下 勇",
            description = "Climbing through sea of clouds to witness 'Goraiko' - the breathtaking holy sunrise atop Mount Fuji.",
            targetLanguage = Language.JAPANESE,
            difficulty = DifficultyLevel.A2,
            coverEmoji = "🗻",
            coverGradientStart = 0xFF0284C7,
            coverGradientEnd = 0xFF4338CA,
            coverTheme = BookCoverTheme.CELESTIAL_INDIGO,
            totalWords = 250,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "夜明け前の富士山頂は冷たい風が吹いていますが、東の空がゆっくりと赤く染まり始めます。",
                    translationText = "Before dawn on Mount Fuji's summit, cold winds blow as the eastern sky slowly turns crimson."
                ),
                BilingualParagraph(
                    targetText = "雲海から黄金色の太陽が昇る瞬間、登山者全員から感動の歓声が上がります。",
                    translationText = "The moment the golden sun rises from the sea of clouds, cheers of awe rise from all climbers."
                )
            )
        ),
        Book(
            id = "ja-10-bushi-do",
            title = "武士道の精神と茶の心",
            translatedTitle = "The Spirit of Bushido and the Way of Tea",
            author = "新渡戸 稲造 (Inazo Nitobe)",
            description = "Harmony, respect, purity, and tranquility in the samurai's tea ceremony pavilion.",
            targetLanguage = Language.JAPANESE,
            difficulty = DifficultyLevel.C1,
            coverEmoji = "🍵",
            coverGradientStart = 0xFF064E3B,
            coverGradientEnd = 0xFF047857,
            coverTheme = BookCoverTheme.NOBLE_EMERALD,
            totalWords = 290,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "武士道において、茶の湯は荒々しい戦乱の中で心の静寂と礼節を取り戻す神聖な場でありました。",
                    translationText = "In Bushido, the tea ceremony was a sacred sanctuary to regain inner serenity and courtesy amidst turbulent wars."
                ),
                BilingualParagraph(
                    targetText = "「和・敬・清・寂」の四文字は、他者への深い思いやりと簡素の中にある美の本質を教えてくれます。",
                    translationText = "The four characters 'Harmony, Respect, Purity, Tranquility' teach profound compassion for others and the essence of beauty in simplicity."
                )
            )
        )
    )
}
