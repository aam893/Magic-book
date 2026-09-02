package com.example.data.sample

import com.example.data.model.BilingualParagraph
import com.example.data.model.Book
import com.example.data.model.BookCoverTheme
import com.example.data.model.DifficultyLevel
import com.example.data.model.Language

object SampleBooksPart5 {
    val list: List<Book> = listOf(
        // ==================== 11. WORLD MYTHS, FABLES & HEROES - 15 Stories ====================
        Book(
            id = "es-11-don-juan",
            title = "El Convidado de Piedra",
            translatedTitle = "The Stone Guest",
            author = "Tirso de Molina",
            description = "The classic Spanish romantic drama of Don Juan Tenorio confronting destiny.",
            targetLanguage = Language.SPANISH,
            difficulty = DifficultyLevel.B2,
            coverEmoji = "🗿",
            coverGradientStart = 0xFF7F1D1D,
            coverGradientEnd = 0xFF312E81,
            coverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
            totalWords = 280,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "En una noche tormentosa en Sevilla, la estatua de piedra del comendador aceptó la temeraria invitación a cenar.",
                    translationText = "On a stormy night in Seville, the stone statue of the commander accepted the reckless invitation to dine."
                ),
                BilingualParagraph(
                    targetText = "Los pasos pesados de mármol retumbaron en la galería, recordando que nadie escapa a sus propias acciones.",
                    translationText = "Heavy marble footsteps echoed through the gallery, reminding that no one escapes their own deeds."
                )
            )
        ),
        Book(
            id = "fr-11-jeanne-darc",
            title = "L'Épopée de Jeanne d'Arc",
            translatedTitle = "The Epic of Joan of Arc",
            author = "Jules Michelet",
            description = "The courageous young peasant girl of Domrémy leading France with unwavering conviction.",
            targetLanguage = Language.FRENCH,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "⚜️",
            coverGradientStart = 0xFF0284C7,
            coverGradientEnd = 0xFFD4AF37,
            coverTheme = BookCoverTheme.DESERT_AMBER,
            totalWords = 270,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Vêtue de son armure blanche étincelante, la jeune Jeanne chevauchait à la tête des troupes vers Orléans.",
                    translationText = "Dressed in her gleaming white armor, young Joan rode at the head of the troops toward Orléans."
                ),
                BilingualParagraph(
                    targetText = "Son étendard flottait au vent, portant l'espoir et le courage dans le cœur de tout un peuple.",
                    translationText = "Her standard fluttered in the wind, carrying hope and courage into the hearts of an entire people."
                )
            )
        ),
        Book(
            id = "de-07-loreley",
            title = "Die Loreley am Rhein",
            translatedTitle = "The Lorelei on the Rhine",
            author = "Heinrich Heine",
            description = "The mythical golden-haired siren singing atop the cliff overlooking the Rhine river.",
            targetLanguage = Language.GERMAN,
            difficulty = DifficultyLevel.A2,
            coverEmoji = "🧜‍♀️",
            coverGradientStart = 0xFF4F46E5,
            coverGradientEnd = 0xFF06B6D4,
            coverTheme = BookCoverTheme.CELESTIAL_INDIGO,
            totalWords = 240,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Ich weiß nicht, was soll es bedeuten, dass ich so traurig bin; ein Märchen aus alten Zeiten, das kommt mir nicht aus dem Sinn.",
                    translationText = "I know not what it means that I am so sorrowful; a fairy tale from ancient times, I cannot get it out of my mind."
                ),
                BilingualParagraph(
                    targetText = "Die Luft ist kühl und es dunkelt, und ruhig fließt der Rhein; der Gipfel des Berges funkelt im Abendsonnenschein.",
                    translationText = "The air is cool and dusk falls, and peacefully flows the Rhine; the peak of the mountain sparkles in the evening sunshine."
                )
            )
        ),
        Book(
            id = "ar-16-majanin-layla",
            title = "مجنون ليلى وقيس بن الملوح",
            translatedTitle = "Majnun Layla and Qays ibn al-Mulawwah",
            author = "تراث الشعر العذري",
            description = "The classic legendary tale of pure devotion and poetry across the Arabian desert.",
            targetLanguage = Language.ARABIC,
            difficulty = DifficultyLevel.B2,
            coverEmoji = "💌",
            coverGradientStart = 0xFF991B1B,
            coverGradientEnd = 0xFF78350F,
            coverTheme = BookCoverTheme.CRIMSON_RUBY,
            totalWords = 270,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "أَمُرُّ عَلَى الدِّيَارِ دِيَارِ لَيْلَى، أُقَبِّلُ ذَا الجِدَارَ وَذَا الجِدَارَا.",
                    translationText = "I pass by the dwellings, the dwellings of Layla, kissing this wall and that wall."
                ),
                BilingualParagraph(
                    targetText = "وَمَا حُبُّ الدِّيَارِ شَغَفْنَ قَلْبِي، وَلَكِنْ حُبُّ مَنْ سَكَنَ الدِّيَارَا.",
                    translationText = "And it is not the love of the houses that enraptures my heart, but the love of the one who dwelt within the houses."
                )
            )
        ),
        Book(
            id = "ja-11-tsuru-ongaeshi",
            title = "鶴の恩返し (The Grateful Crane)",
            translatedTitle = "The Grateful Crane's Return of Favor",
            author = "Japanese Fairy Tale",
            description = "A rescued crane weaves breathtaking cloth from her own feathers out of gratitude.",
            targetLanguage = Language.JAPANESE,
            difficulty = DifficultyLevel.A2,
            coverEmoji = "🕊️",
            coverGradientStart = 0xFF0284C7,
            coverGradientEnd = 0xFF064E3B,
            coverTheme = BookCoverTheme.CELESTIAL_INDIGO,
            totalWords = 260,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "雪の降る寒い日、心優しい若者が罠にかかった一羽の美しい鶴を助けてやりました。",
                    translationText = "On a cold snowy day, a kind-hearted youth rescued a beautiful crane caught in a trap."
                ),
                BilingualParagraph(
                    targetText = "その夜、美しい娘が訪ねてきて、素晴らしい千羽鶴の織物を織り始めました。",
                    translationText = "That night, a beautiful maiden visited and began weaving splendid cloth of a thousand cranes."
                )
            )
        ),
        Book(
            id = "it-11-casanova",
            title = "Il Carnevale delle Maschere",
            translatedTitle = "The Carnival of Masks",
            author = "Lorenzo Da Ponte",
            description = "Gilded Venetian masks, candlelit masquerade balls, and silk capes in 18th-century Venice.",
            targetLanguage = Language.ITALIAN,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "🎭",
            coverGradientStart = 0xFF4F46E5,
            coverGradientEnd = 0xFFEC4899,
            coverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
            totalWords = 250,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Durante il Carnevale di Venezia, nobili e poeti nascondevano i loro volti dietro splendide maschere decorate d'oro.",
                    translationText = "During the Carnival of Venice, nobles and poets hid their faces behind splendid masks decorated with gold."
                ),
                BilingualParagraph(
                    targetText = "Nei saloni affrescati affacciati sui canali, la musica barocca accompagnava danze misteriose fino all'alba.",
                    translationText = "In frescoed salons overlooking the canals, baroque music accompanied mysterious dances until dawn."
                )
            )
        ),
        Book(
            id = "pt-11-cristo-redentor",
            title = "O Sol Nascente na Baía de Guanabara",
            translatedTitle = "The Rising Sun in Guanabara Bay",
            author = "Carlos Drummond",
            description = "Morning golden mist parting over Sugarloaf Mountain and emerald Atlantic waters.",
            targetLanguage = Language.PORTUGUESE,
            difficulty = DifficultyLevel.A1,
            coverEmoji = "🌅",
            coverGradientStart = 0xFFD97706,
            coverGradientEnd = 0xFF047857,
            coverTheme = BookCoverTheme.DESERT_AMBER,
            totalWords = 210,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "O nascer do sol tinge o Pão de Açúcar com tons de dourado e laranja suave enquanto os barcos zarpam.",
                    translationText = "The sunrise tints Sugarloaf Mountain in shades of gold and soft orange as boats set sail."
                ),
                BilingualParagraph(
                    targetText = "A brisa do mar traz o aroma da maresia e a promessa de um dia radiante na praia.",
                    translationText = "The sea breeze brings the scent of ocean spray and the promise of a radiant day on the beach."
                )
            )
        ),
        Book(
            id = "en-11-moby-dick",
            title = "The White Whale of the Pacific",
            translatedTitle = "The White Whale of the Pacific",
            author = "Herman Melville",
            description = "Captain Ahab's legendary pursuit across tempestuous oceans aboard the Pequod.",
            targetLanguage = Language.ENGLISH,
            difficulty = DifficultyLevel.C1,
            coverEmoji = "🐋",
            coverGradientStart = 0xFF0F172A,
            coverGradientEnd = 0xFF0284C7,
            coverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
            totalWords = 300,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Call me Ishmael. Some years ago—never mind how long precisely—having little money in my purse, I thought I would sail about a little and see the watery part of the world.",
                    translationText = "Appelez-moi Ismaël. Il y a quelques années—peu importe combien exactement—ayant peu d'argent en poche, je résolus de naviguer un peu pour contempler la partie aquatique du monde."
                ),
                BilingualParagraph(
                    targetText = "Whenever it is a damp, drizzly November in my soul, I account it high time to get to sea as soon as I can.",
                    translationText = "Chaque fois qu'il fait un novembre brumeux et humide dans mon âme, j'estime qu'il est grand temps de prendre la mer au plus vite."
                )
            )
        ),
        Book(
            id = "zh-11-feng-shen-bang",
            title = "封神演义：哪吒闹海",
            translatedTitle = "Investiture of the Gods: Nezha Conquers the Dragon King",
            author = "许仲琳 (Xu Zhonglin)",
            description = "Young Nezha with his Universal Ring and Wind Fire Wheels defending fishermen from the Dragon Palace.",
            targetLanguage = Language.CHINESE,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "🔥",
            coverGradientStart = 0xFFDC2626,
            coverGradientEnd = 0xFFD97706,
            coverTheme = BookCoverTheme.CRIMSON_RUBY,
            totalWords = 270,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "小英雄哪吒脚踏风火轮，手持乾坤圈，在东海之滨掀起滔天巨浪。",
                    translationText = "Young hero Nezha stepped on the Wind Fire Wheels, holding the Universe Ring, stirring towering waves by the Eastern Sea."
                ),
                BilingualParagraph(
                    targetText = "他惩恶扬善，勇斗龙王三太子，保护了海边渔民的安宁生活。",
                    translationText = "He championed righteousness, bravely fought the dragon prince, and protected the peaceful life of the coastal fishermen."
                )
            )
        ),
        Book(
            id = "ru-11-sadko",
            title = "Былина о купце Садко",
            translatedTitle = "The Epic Bylina of Sadko the Merchant",
            author = "Древнерусский былинный эпос",
            description = "Sadko playing his enchanted gusli harp for the Tsar of the Sea in the deep crystal palace.",
            targetLanguage = Language.RUSSIAN,
            difficulty = DifficultyLevel.B2,
            coverEmoji = "🪕",
            coverGradientStart = 0xFF0284C7,
            coverGradientEnd = 0xFF065F46,
            coverTheme = BookCoverTheme.CELESTIAL_INDIGO,
            totalWords = 280,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Играл новгородский гусляр Садко на своих звонких гуслях на берегу Ильмень-озера так сладко, что сам Морской Царь заслушался.",
                    translationText = "The Novgorod gusli player Sadko played so sweetly on the shores of Lake Ilmen that the Sea Tsar himself listened in rapture."
                ),
                BilingualParagraph(
                    targetText = "Царь Морской пригласил музыканта в свой подводный хрустальный терем и одарил несметными сокровищами.",
                    translationText = "The Sea Tsar invited the musician into his underwater crystal palace and bestowed upon him boundless treasures."
                )
            )
        ),
        Book(
            id = "ar-17-taj-al-arous",
            title = "بلاغة اللغة العربية وجمالها",
            translatedTitle = "The Eloquence and Beauty of Arabic",
            author = "طه حسين",
            description = "A tribute to the richness of Arabic vocabulary, metaphors, and rhythm.",
            targetLanguage = Language.ARABIC,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "✨",
            coverGradientStart = 0xFF3E2723,
            coverGradientEnd = 0xFFB45309,
            coverTheme = BookCoverTheme.VINTAGE_LEATHER,
            totalWords = 240,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "إن لغتنا العربية كنز لا ينفد من المفردات والمعاني الدقيقة التي تعبر عن أرق المشاعر الإنسانية.",
                    translationText = "Our Arabic language is an inexhaustible treasure of precise vocabulary and nuances expressing the subtlest human emotions."
                ),
                BilingualParagraph(
                    targetText = "في كل حرف منها رنين موسيقي يلامس القلوب ويسحر العقول بحسن بيانه.",
                    translationText = "In each of its letters is a musical resonance that touches hearts and enchants minds with sheer eloquence."
                )
            )
        ),
        Book(
            id = "es-12-la-alhambra",
            title = "Cuentos de la Alhambra",
            translatedTitle = "Tales of the Alhambra",
            author = "Washington Irving",
            description = "Romantic legends of Moorish princes and hidden enchanted halls in Andalusia.",
            targetLanguage = Language.SPANISH,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "🏰",
            coverGradientStart = 0xFF78350F,
            coverGradientEnd = 0xFF047857,
            coverTheme = BookCoverTheme.NOBLE_EMERALD,
            totalWords = 260,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "A la luz de la luna llena, los patios de la Alhambra susurran leyendas de caballeros moriscos y tesoros escondidos.",
                    translationText = "Under the light of the full moon, the courtyards of the Alhambra whisper legends of Moorish knights and hidden treasures."
                ),
                BilingualParagraph(
                    targetText = "Las fuentes de mármol siguen vertiendo agua cristalina como lo hacían hace setecientos años.",
                    translationText = "The marble fountains continue to pour crystalline water just as they did seven hundred years ago."
                )
            )
        ),
        Book(
            id = "fr-12-notre-dame",
            title = "Notre-Dame de Paris",
            translatedTitle = "The Hunchback of Notre-Dame",
            author = "Victor Hugo",
            description = "Quasimodo and the bells of the Gothic cathedral overlooking the rooftops of medieval Paris.",
            targetLanguage = Language.FRENCH,
            difficulty = DifficultyLevel.B2,
            coverEmoji = "🔔",
            coverGradientStart = 0xFF312E81,
            coverGradientEnd = 0xFF7F1D1D,
            coverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
            totalWords = 290,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "La cathédrale s'élevait comme un géant de pierre au-dessus des toits serrés de la vieille cité de Paris.",
                    translationText = "The cathedral rose like a stone giant above the tightly packed rooftops of the old city of Paris."
                ),
                BilingualParagraph(
                    targetText = "Du haut des tours gothiques, les grandes cloches de bronze sonnaient à toute volée dans le ciel étoilé.",
                    translationText = "From atop the Gothic towers, the great bronze bells rang out fully into the starry sky."
                )
            )
        ),
        Book(
            id = "de-08-wilhelm-tell",
            title = "Wilhelm Tell und der Apfelschuss",
            translatedTitle = "William Tell and the Apple Shot",
            author = "Friedrich Schiller",
            description = "The Swiss folk hero who struck an apple on his son's head with a crossbow bolt for freedom.",
            targetLanguage = Language.GERMAN,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "🎯",
            coverGradientStart = 0xFF047857,
            coverGradientEnd = 0xFFB45309,
            coverTheme = BookCoverTheme.NOBLE_EMERALD,
            totalWords = 270,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Mit ruhiger Hand spannte Wilhelm Tell seine Armbrust auf dem Marktplatz von Altdorf.",
                    translationText = "With a steady hand, William Tell spanned his crossbow on the market square of Altdorf."
                ),
                BilingualParagraph(
                    targetText = "Der Pfeil spaltete den Apfel auf dem Kopf seines Knaben genau in zwei Hälften.",
                    translationText = "The arrow split the apple on his boy's head precisely into two halves."
                )
            )
        ),
        Book(
            id = "ja-12-genji-monogatari",
            title = "源氏物語の雅やかな世界",
            translatedTitle = "The Elegant World of the Tale of Genji",
            author = "紫式部 (Murasaki Shikibu)",
            description = "Silk kimono robes, court poetry, and autumn moonlight in ancient Heian-kyo (Kyoto).",
            targetLanguage = Language.JAPANESE,
            difficulty = DifficultyLevel.C1,
            coverEmoji = "🪭",
            coverGradientStart = 0xFFEC4899,
            coverGradientEnd = 0xFF312E81,
            coverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
            totalWords = 280,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "平安京の宮廷では、四季折々の移ろいに心を寄せる繊細な美意識が育まれました。",
                    translationText = "In the Heian-kyo imperial court, a delicate aesthetic sensibility attuned to seasonal changes was cultivated."
                ),
                BilingualParagraph(
                    targetText = "秋の月を見上げて詠まれる和歌は、千年の時を超えて今も人々の心に深く響き渡ります。",
                    translationText = "Waka poems composed while gazing at the autumn moon still resonate deeply in people's hearts across a thousand years."
                )
            )
        )
    )
}
