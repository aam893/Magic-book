package com.example.data.sample

import com.example.data.model.BilingualParagraph
import com.example.data.model.Book
import com.example.data.model.BookCoverTheme
import com.example.data.model.DifficultyLevel
import com.example.data.model.Language

object SampleBooksPart4 {
    val list: List<Book> = listOf(
        // ==================== 9. CHINESE (中文) - 10 Stories ====================
        Book(
            id = "zh-01-monkey-king",
            title = "孙悟空大闹天宫 (Monkey King)",
            translatedTitle = "The Monkey King Creates Havoc in Heaven",
            author = "吴承恩 (Wu Cheng'en)",
            description = "Sun Wukong masters 72 magical transformations and somersault clouds in the classic Journey to the West.",
            targetLanguage = Language.CHINESE,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "🐵",
            coverGradientStart = 0xFF7F1D1D,
            coverGradientEnd = 0xFF991B1B,
            coverTheme = BookCoverTheme.CRIMSON_RUBY,
            totalWords = 280,
            explicitPageCount = 3,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "在东胜神洲傲来国花果山顶上，有一块仙石，吸收天地之灵气，日月之精华。",
                    translationText = "Atop the Flower Fruit Mountain in the country of Aolai, there stood an immortal stone that absorbed the spirit of heaven and earth."
                ),
                BilingualParagraph(
                    targetText = "一日仙石迸裂，产一石卵，化作一个石猴。他眼运金光，射冲斗府，拜四方后便在山中欢喜跳跃。",
                    translationText = "One day the stone split open, birthing a stone egg that transformed into a stone monkey with golden light beaming from his eyes."
                ),
                BilingualParagraph(
                    targetText = "后来他学得七十二般变化与筋斗云，一个筋斗便能飞出十万八千里！",
                    translationText = "Later he mastered seventy-two earthly transformations and the somersault cloud, traveling 108,000 li in a single leap!"
                )
            )
        ),
        Book(
            id = "zh-02-shou-zhu-dai-tu",
            title = "守株待兔 (Waiting for a Rabbit)",
            translatedTitle = "Waiting by the Tree Stump for a Rabbit",
            author = "韩非子 (Han Feizi)",
            description = "A famous ancient philosophical idiom warning against relying on mere luck instead of diligent effort.",
            targetLanguage = Language.CHINESE,
            difficulty = DifficultyLevel.A1,
            coverEmoji = "🐇",
            coverGradientStart = 0xFF064E3B,
            coverGradientEnd = 0xFF047857,
            coverTheme = BookCoverTheme.NOBLE_EMERALD,
            totalWords = 220,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "宋国有个农夫在田里耕作。田边有一棵大树的树桩。",
                    translationText = "There was a farmer in the state of Song working in his field. By the field stood the stump of a large tree."
                ),
                BilingualParagraph(
                    targetText = "一只跑得很急的兔子不小心撞在树桩上，折断了脖子死去了。农夫白白得到了一只兔子，心里非常高兴。",
                    translationText = "A swiftly running rabbit accidentally collided with the tree stump, broke its neck, and died. The farmer got a rabbit for free and was overjoyed."
                ),
                BilingualParagraph(
                    targetText = "从此他放下锄头，整天守在树桩旁，希望再捡到兔子，结果田地都荒芜了。",
                    translationText = "From then on, he put down his plow and waited by the stump all day hoping to catch another rabbit, while his fields turned barren."
                )
            )
        ),
        Book(
            id = "zh-03-yu-gong-yi-shan",
            title = "愚公移山 (The Foolish Old Man)",
            translatedTitle = "The Foolish Old Man Moves the Mountain",
            author = "列子 (Liezi)",
            description = "The immortal Chinese fable illustrating that unbreakable persistence can move the highest mountains.",
            targetLanguage = Language.CHINESE,
            difficulty = DifficultyLevel.A2,
            coverEmoji = "⛰️",
            coverGradientStart = 0xFF78350F,
            coverGradientEnd = 0xFFB45309,
            coverTheme = BookCoverTheme.DESERT_AMBER,
            totalWords = 250,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "古时候有位叫愚公的老人，年近九十岁。他门前有两座大山挡住了出行的道路。",
                    translationText = "In ancient times there was an old man named Yugong, nearly ninety years old. Two high mountains stood before his door blocking the path."
                ),
                BilingualParagraph(
                    targetText = "愚公带领子孙每天挖山运土。别人笑他傻，他回答说：'子子孙孙无穷无尽，而山不会再长高，何愁移不平呢？'",
                    translationText = "Yugong led his descendants every day to dig and carry the earth. When mocked, he answered: 'Generations of children will never end, while mountains cannot grow taller, why worry about not leveling them?'"
                )
            )
        ),
        Book(
            id = "zh-04-chaye-wenhua",
            title = "茶经与中国茶道",
            translatedTitle = "The Classic of Tea and the Chinese Tea Way",
            author = "陆羽 (Lu Yu)",
            description = "Boiling mountain springs, green dragon well leaves, and the spiritual harmony of tea culture.",
            targetLanguage = Language.CHINESE,
            difficulty = DifficultyLevel.B2,
            coverEmoji = "🍵",
            coverGradientStart = 0xFF065F46,
            coverGradientEnd = 0xFF047857,
            coverTheme = BookCoverTheme.NOBLE_EMERALD,
            totalWords = 270,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "茶之为饮，发乎神农氏，闻于鲁周公。水为茶之母，器为茶之父。",
                    translationText = "Tea as a drink originated with Emperor Shennong and was praised by the Duke of Zhou. Water is the mother of tea, vessels its father."
                ),
                BilingualParagraph(
                    targetText = "取山泉清冽之水，烹龙井翠绿之叶，静心细品，方知天地自然之至味。",
                    translationText = "Using pure mountain spring water to brew emerald Longjing leaves, one savors the true flavor of nature in mindful serenity."
                )
            )
        ),
        Book(
            id = "zh-05-san-guo-yan-yi",
            title = "三国演义：草船借箭",
            translatedTitle = "Romance of the Three Kingdoms: Borrowing Arrows with Straw Boats",
            author = "罗贯中 (Luo Guanzhong)",
            description = "Zhuge Liang's brilliant tactical trick maneuvering straw-covered boats through Yangtze river fog.",
            targetLanguage = Language.CHINESE,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "🏹",
            coverGradientStart = 0xFF0284C7,
            coverGradientEnd = 0xFF0F172A,
            coverTheme = BookCoverTheme.CELESTIAL_INDIGO,
            totalWords = 290,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "长江之上大雾弥漫，诸葛亮命令二十只草船一字排开，逼近曹操水寨擂鼓呐喊。",
                    translationText = "Dense fog rolled across the Yangtze River as Zhuge Liang ordered twenty straw-covered boats to line up and beat war drums near Cao Cao's camp."
                ),
                BilingualParagraph(
                    targetText = "曹军看不清水中虚实，乱箭齐发，十余万支雕翎箭全射在草船的草靶之上。",
                    translationText = "Unable to see in the thick mist, Cao's troops fired countless volleys of arrows, all lodging securely into the straw targets."
                )
            )
        ),
        Book(
            id = "zh-06-gugong-beijing",
            title = "紫禁城的红墙与琉璃瓦",
            translatedTitle = "Red Walls and Glazed Tiles of the Forbidden City",
            author = "王振华",
            description = "Imperial courtyards, golden dragon thrones, and six centuries of imperial Chinese history in Beijing.",
            targetLanguage = Language.CHINESE,
            difficulty = DifficultyLevel.A2,
            coverEmoji = "🏯",
            coverGradientStart = 0xFF991B1B,
            coverGradientEnd = 0xFFD97706,
            coverTheme = BookCoverTheme.CRIMSON_RUBY,
            totalWords = 240,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "北京故宫太和殿在晨光中庄严耸立，金色琉璃瓦在阳光下熠熠生辉。",
                    translationText = "The Hall of Supreme Harmony in Beijing's Forbidden City stands solemnly in morning light, its golden glazed tiles gleaming."
                ),
                BilingualParagraph(
                    targetText = "漫步在红墙回廊之间，古老的铜缸与汉白玉雕栏见证了数百年的岁月沧桑。",
                    translationText = "Strolling between crimson walls and pavilions, ancient bronze vats and white jade balustrades bear witness to centuries of history."
                )
            )
        ),
        Book(
            id = "zh-07-li-bai-yue",
            title = "李白：月下独酌与诗意",
            translatedTitle = "Li Bai: Drinking Alone Beneath the Moon",
            author = "李白 (Li Bai)",
            description = "The romantic Tang dynasty poet raising a jade cup to the moon and his shadow in spring.",
            targetLanguage = Language.CHINESE,
            difficulty = DifficultyLevel.C1,
            coverEmoji = "🌙",
            coverGradientStart = 0xFF1E1B4B,
            coverGradientEnd = 0xFF312E81,
            coverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
            totalWords = 280,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "花间一壶酒，独酌无相亲。举杯邀明月，对影成三人。",
                    translationText = "Among flowers with a pot of wine, I drink alone with no companion. Raising my cup, I invite the bright moon, facing my shadow making three."
                ),
                BilingualParagraph(
                    targetText = "月既不解饮，影徒随我身。暂伴月将影，行乐须及春。",
                    translationText = "The moon knows not how to drink, and the shadow merely follows my form. Yet companioning moon and shadow for now, joyful revelry must seize the spring."
                )
            )
        ),
        Book(
            id = "zh-08-panda-sichuan",
            title = "大熊猫与四川竹林",
            translatedTitle = "Giant Pandas and Sichuan Bamboo Forests",
            author = "张秀兰",
            description = "Gentle giant pandas munching fresh green bamboo shoots in the misty mountains of Chengdu.",
            targetLanguage = Language.CHINESE,
            difficulty = DifficultyLevel.A1,
            coverEmoji = "🐼",
            coverGradientStart = 0xFF047857,
            coverGradientEnd = 0xFF064E3B,
            coverTheme = BookCoverTheme.NOBLE_EMERALD,
            totalWords = 210,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "在四川青翠的竹林里，黑白相间的大熊猫正在悠闲地吃着新鲜的竹笋。",
                    translationText = "In the lush bamboo forests of Sichuan, black and white giant pandas leisurely eat fresh green bamboo shoots."
                ),
                BilingualParagraph(
                    targetText = "小熊猫在草地上滚来滚去，憨态可掬的样子惹得游人们开怀大笑。",
                    translationText = "Cubs roll around on the grass, their adorable playful antics bringing cheerful laughter to all visitors."
                )
            )
        ),
        Book(
            id = "zh-09-chang-cheng",
            title = "万里长城的巨龙奇迹",
            translatedTitle = "The Giant Dragon Wonder of the Great Wall",
            author = "刘明远",
            description = "Ancient watchtowers winding like a stone dragon over rolling northern mountain ridges.",
            targetLanguage = Language.CHINESE,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "🧱",
            coverGradientStart = 0xFF78350F,
            coverGradientEnd = 0xFF3E2723,
            coverTheme = BookCoverTheme.VINTAGE_LEATHER,
            totalWords = 260,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "万里长城宛如一条威武的巨龙，盘旋在崇山峻岭之巅，绵延数万里。",
                    translationText = "The Great Wall resembles a majestic giant dragon winding over soaring mountain ridges for thousands of miles."
                ),
                BilingualParagraph(
                    targetText = "古老的烽火台在夕阳下投出长长的影子，诉说着中华民族坚韧不拔的意志。",
                    translationText = "Ancient beacon watchtowers cast long shadows at sunset, speaking of the unyielding perseverance of the nation."
                )
            )
        ),
        Book(
            id = "zh-10-gu-qin-zhi-yin",
            title = "高山流水遇知音",
            translatedTitle = "High Mountains and Flowing Water: Finding a True Friend",
            author = "列子·汤问",
            description = "The famous legend of Bo Ya's zither and Zhong Ziqi, honoring the rarest spiritual friendship.",
            targetLanguage = Language.CHINESE,
            difficulty = DifficultyLevel.B2,
            coverEmoji = "🎼",
            coverGradientStart = 0xFF4F46E5,
            coverGradientEnd = 0xFF06B6D4,
            coverTheme = BookCoverTheme.CELESTIAL_INDIGO,
            totalWords = 270,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "伯牙善鼓琴，钟子期善听。伯牙志在高山，钟子期曰：'善哉！峨峨兮若泰山！'",
                    translationText = "Bo Ya was skilled at playing the zither, and Zhong Ziqi was skilled at listening. When Bo Ya's mind was on high mountains, Ziqi said: 'Marvelous! Lofty as Mount Tai!'"
                ),
                BilingualParagraph(
                    targetText = "伯牙志在流水，钟子期曰：'善哉！洋洋兮若江河！'世上知音难求，千古传为佳话。",
                    translationText = "When Bo Ya's intent was on flowing water, Ziqi exclaimed: 'Marvelous! Vast and rushing as great rivers!' True understanding friends are rare."
                )
            )
        ),

        // ==================== 10. RUSSIAN (Русский) - 10 Stories ====================
        Book(
            id = "ru-01-zolotaya-rybka",
            title = "Сказка о рыбаке и рыбке",
            translatedTitle = "The Tale of the Fisherman and the Golden Fish",
            author = "Александр Пушкин (Pushkin)",
            description = "Pushkin's classic moral fairy tale of an enchanted wish-granting golden fish and the boundless greed of an old woman.",
            targetLanguage = Language.RUSSIAN,
            difficulty = DifficultyLevel.A2,
            coverEmoji = "🐟",
            coverGradientStart = 0xFF0284C7,
            coverGradientEnd = 0xFFD4AF37,
            coverTheme = BookCoverTheme.DESERT_AMBER,
            totalWords = 280,
            explicitPageCount = 3,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Жил старик со своею старухой у самого синего моря; они жили в ветхой землянке ровно тридцать лет и три года.",
                    translationText = "An old man lived with his old woman by the very blue sea; they lived in a dilapidated dugout for exactly thirty-three years."
                ),
                BilingualParagraph(
                    targetText = "Старик ловил неводом рыбу, старуха пряла свою пряжу. Раз он в море закинул невод, — пришёл невод с одной рыбкой, непростою рыбкой, — золотою.",
                    translationText = "The old man caught fish with a net, the old woman spun her yarn. Once he cast the net into the sea — it came back with a single fish, no ordinary fish, but a golden one."
                ),
                BilingualParagraph(
                    targetText = "Голосом молвит человечьим: «Отпусти ты, старче, меня в море! Дорогой за себя дам откуп: откуплюсь чем только пожелаешь».",
                    translationText = "It spoke in a human voice: «Release me, old man, into the sea! I will pay a great ransom: I will grant whatever you wish»."
                )
            )
        ),
        Book(
            id = "ru-02-belye-nochi",
            title = "Белые Ночи Петербурга",
            translatedTitle = "White Nights of St. Petersburg",
            author = "Фёдор Достоевский (Dostoevsky)",
            description = "A dreamer wandering along the luminous canals and romantic granite embankments of the Neva river.",
            targetLanguage = Language.RUSSIAN,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "🌉",
            coverGradientStart = 0xFF1E293B,
            coverGradientEnd = 0xFF4338CA,
            coverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
            totalWords = 300,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Была чудная ночь, такая ночь, которая разве только и может быть тогда, когда мы молоды, любезный читатель.",
                    translationText = "It was a wonderful night, such a night as can perhaps only happen when we are young, dear reader."
                ),
                BilingualParagraph(
                    targetText = "Небо было такое звёздное, такое светлое небо, что, взглянув на него, невольно нужно было спросить себя: неужели же могут жить под таким небом сердитые и капризные люди?",
                    translationText = "The sky was so starry, so luminous, that looking up at it one could not help asking oneself: can angry and capricious people really live under such a sky?"
                )
            )
        ),
        Book(
            id = "ru-03-kolobok",
            title = "Сказка про Колобка",
            translatedTitle = "The Story of Kolobok (The Little Bun)",
            author = "Русская народная сказка",
            description = "The singing golden round bun rolling through the woods escaping the hare, wolf, and bear.",
            targetLanguage = Language.RUSSIAN,
            difficulty = DifficultyLevel.A1,
            coverEmoji = "🫓",
            coverGradientStart = 0xFFD97706,
            coverGradientEnd = 0xFFB45309,
            coverTheme = BookCoverTheme.DESERT_AMBER,
            totalWords = 220,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Жили-были дедушка да бабушка. Испекла бабушка круглый и румяный колобок и положила его на окошко остывать.",
                    translationText = "Once upon a time lived grandfather and grandmother. Grandmother baked a round, rosy bun and placed it on the windowsill to cool."
                ),
                BilingualParagraph(
                    targetText = "Колобок полежал-полежал, да и покатился с окна на завалинку, с завалинки на травку, а с травки на тропинку в лес.",
                    translationText = "Kolobok lay for a while, then rolled from the window to the earthen bank, from the bank to the grass, and onto the forest path."
                )
            )
        ),
        Book(
            id = "ru-04-zimnee-utro",
            title = "Зимнее Утро в Деревне",
            translatedTitle = "Winter Morning in the Countryside",
            author = "Александр Пушкин",
            description = "Frost and sun, sparkling silver snow, and cozy crackling birchwood in the stove.",
            targetLanguage = Language.RUSSIAN,
            difficulty = DifficultyLevel.A2,
            coverEmoji = "❄️",
            coverGradientStart = 0xFF0284C7,
            coverGradientEnd = 0xFF0F172A,
            coverTheme = BookCoverTheme.CELESTIAL_INDIGO,
            totalWords = 240,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Мороз и солнце; день чудесный! Еще ты дремлешь, друг прелестный — пора, красавица, проснись!",
                    translationText = "Frost and sun; a wonderful day! Still you slumber, lovely friend — it is time, beauty, awake!"
                ),
                BilingualParagraph(
                    targetText = "Под голубыми небесами великолепными коврами, блестя на солнце, снег лежит; прозрачный лес один чернеет.",
                    translationText = "Beneath blue skies in magnificent carpets, sparkling in the sun, snow lies; the transparent forest alone shows dark."
                )
            )
        ),
        Book(
            id = "ru-05-master-i-margarita",
            title = "Мастер и Маргарита: Патриаршие",
            translatedTitle = "The Master and Margarita: Patriarch's Ponds",
            author = "Михаил Булгаков (Bulgakov)",
            description = "A mysterious foreign gentleman appears under the linden trees of Moscow at sunset.",
            targetLanguage = Language.RUSSIAN,
            difficulty = DifficultyLevel.B2,
            coverEmoji = "🐈‍⬛",
            coverGradientStart = 0xFF1E1B4B,
            coverGradientEnd = 0xFF0F172A,
            coverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
            totalWords = 310,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Однажды весною, в час небывало жаркого заката, в Москве, на Патриарших прудах, появились два гражданина.",
                    translationText = "One spring day, at the hour of an unprecedentedly hot sunset, two citizens appeared at Patriarch's Ponds in Moscow."
                ),
                BilingualParagraph(
                    targetText = "Никто не мог предположить, что этот странный вечер навсегда изменит жизнь всего города.",
                    translationText = "No one could have guessed that this strange evening would forever change the life of the entire city."
                )
            )
        ),
        Book(
            id = "ru-06-konek-gorbunok",
            title = "Конёк-Горбунок",
            translatedTitle = "The Little Humpbacked Horse",
            author = "Пётр Ершов (Ershov)",
            description = "The magical miniature flying horse helping kind Ivan overcome impossible trials and catch the Firebird.",
            targetLanguage = Language.RUSSIAN,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "🐴",
            coverGradientStart = 0xFFD4AF37,
            coverGradientEnd = 0xFF78350F,
            coverTheme = BookCoverTheme.DESERT_AMBER,
            totalWords = 280,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "За горами, за лесами, за широкими морями, против неба — на земле жил старик в одном селе.",
                    translationText = "Beyond mountains, beyond forests, beyond wide seas, beneath the sky on earth lived an old man in a village."
                ),
                BilingualParagraph(
                    targetText = "Младший сын Иван подружился с волшебным Коньком-Горбунком, который умел летать быстрее ветра.",
                    translationText = "The youngest son Ivan befriended the magical Little Humpbacked Horse who could fly swifter than the wind."
                )
            )
        ),
        Book(
            id = "ru-07-samovar-chai",
            title = "Традиции Русского Самовара",
            translatedTitle = "Traditions of the Russian Samovar",
            author = "Елена Смирнова",
            description = "Boiling hot tea from an ornate copper samovar served with honey, berries, and sushki in the garden.",
            targetLanguage = Language.RUSSIAN,
            difficulty = DifficultyLevel.A1,
            coverEmoji = "🫖",
            coverGradientStart = 0xFF78350F,
            coverGradientEnd = 0xFFB45309,
            coverTheme = BookCoverTheme.DESERT_AMBER,
            totalWords = 210,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "На столе весело шумит начищенный до блеска медный самовар с горячим чаем из лесных трав.",
                    translationText = "On the table, a polished copper samovar sings cheerfully with hot tea brewed from forest herbs."
                ),
                BilingualParagraph(
                    targetText = "Вся семья собирается вместе, пьёт чай с липовым мёдом и хрустящими сушками, беседуя о добрых новостях.",
                    translationText = "The whole family gathers together, drinking tea with linden honey and crisp bread-rings, chatting about good news."
                )
            )
        ),
        Book(
            id = "ru-08-tajga-sibir",
            title = "Тайна Сибирской Тайги",
            translatedTitle = "Mystery of the Siberian Taiga",
            author = "Дмитрий Орлов",
            description = "Endless cedar forests, crystal-clear Lake Baikal, and majestic brown bears in the Siberian wilderness.",
            targetLanguage = Language.RUSSIAN,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "🌲",
            coverGradientStart = 0xFF064E3B,
            coverGradientEnd = 0xFF047857,
            coverTheme = BookCoverTheme.NOBLE_EMERALD,
            totalWords = 260,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Озеро Байкал — самое глубокое и чистое озеро на Земле, окружённое вековыми кедровыми лесами.",
                    translationText = "Lake Baikal is the deepest and clearest lake on Earth, encircled by ancient cedar forests."
                ),
                BilingualParagraph(
                    targetText = "Зимой его прозрачный лед превращается в сверкающее зеркало с причудливыми морозными узорами.",
                    translationText = "In winter its transparent ice turns into a sparkling mirror with intricate frosted patterns."
                )
            )
        ),
        Book(
            id = "ru-09-anna-karenina",
            title = "Анна Каренина: Зимний Бал",
            translatedTitle = "Anna Karenina: The Winter Ball",
            author = "Лев Толстой (Leo Tolstoy)",
            description = "Chandeliers, waltz violins, and dramatic encounters at a grand 19th-century Moscow society ball.",
            targetLanguage = Language.RUSSIAN,
            difficulty = DifficultyLevel.C1,
            coverEmoji = "💃",
            coverGradientStart = 0xFF991B1B,
            coverGradientEnd = 0xFF4338CA,
            coverTheme = BookCoverTheme.CRIMSON_RUBY,
            totalWords = 300,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Анна была в черном бархатном платье, открывавшем её точеные плечи и полную грации осанку.",
                    translationText = "Anna wore a black velvet gown revealing her sculpted shoulders and full graceful posture."
                ),
                BilingualParagraph(
                    targetText = "Музыка вальса кружила пары в сиянии сотен хрустальных свечей, отражавшихся в высоких зеркалах.",
                    translationText = "Waltz music whirled couples in the radiance of hundreds of crystal candles reflecting in tall mirrors."
                )
            )
        ),
        Book(
            id = "ru-10-kosmos-gagarin",
            title = "Первый Человек в Космосе",
            translatedTitle = "The First Human in Space",
            author = "Алексей Леонов",
            description = "Yuri Gagarin's historic 108-minute orbit around Earth with his famous phrase: 'Поехали!'.",
            targetLanguage = Language.RUSSIAN,
            difficulty = DifficultyLevel.A2,
            coverEmoji = "🚀",
            coverGradientStart = 0xFF0F172A,
            coverGradientEnd = 0xFF0284C7,
            coverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
            totalWords = 240,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Двенадцатого апреля тысяча девятьсот шестьдесят первого года Юрий Гагарин сказал знаменитое слово: «Поехали!»",
                    translationText = "On April 12, 1961, Yuri Gagarin uttered the famous word: «Poyekhali! (Let's go!)»"
                ),
                BilingualParagraph(
                    targetText = "Увидев нашу планету из иллюминатора корабля 'Восток-1', он воскликнул: 'Красота-то какая! Земля прекрасна!'",
                    translationText = "Seeing our planet from the porthole of Vostok-1, he exclaimed: 'What immense beauty! Earth is magnificent!'"
                )
            )
        )
    )
}
