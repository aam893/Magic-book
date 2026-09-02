package com.example.data.sample

import com.example.data.model.BilingualParagraph
import com.example.data.model.Book
import com.example.data.model.BookCoverTheme
import com.example.data.model.DifficultyLevel
import com.example.data.model.Language

object SampleBooksPart1 {
    val list: List<Book> = listOf(
        // ==================== 1. SPANISH (Español) - 10 Stories ====================
        Book(
            id = "es-01-principito",
            title = "El Principito",
            translatedTitle = "The Little Prince",
            author = "Antoine de Saint-Exupéry",
            description = "A poetic tale of a young prince who visits various planets in space, addressing themes of loneliness, friendship, love, and loss.",
            targetLanguage = Language.SPANISH,
            difficulty = DifficultyLevel.A2,
            coverEmoji = "👑",
            coverGradientStart = 0xFF4F46E5,
            coverGradientEnd = 0xFFEC4899,
            coverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
            totalWords = 350,
            explicitPageCount = 4,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Pido perdón a los niños por haber dedicado este libro a una persona grande. Tengo una seria razón para ello: esta persona grande es el mejor amigo que tengo en el mundo.",
                    translationText = "I ask children's forgiveness for having dedicated this book to a grown-up. I have a serious reason for doing so: this grown-up is the best friend I have in the world."
                ),
                BilingualParagraph(
                    targetText = "Tengo otra razón: esta persona grande puede entenderlo todo, incluso los libros para niños. Tengo una tercera razón: esta persona grande vive en Francia, donde pasa hambre y frío.",
                    translationText = "I have another reason: this grown-up can understand everything, even books for children. I have a third reason: this grown-up lives in France, where he is hungry and cold."
                ),
                BilingualParagraph(
                    targetText = "Viví así, solo, sin nadie con quien hablar verdaderamente, hasta un aterrizaje forzoso en el desierto de Sahara, hace seis años.",
                    translationText = "I lived like this, alone, with no one to really talk to, until an emergency landing in the Sahara desert six years ago."
                ),
                BilingualParagraph(
                    targetText = "Imaginen entonces mi sorpresa cuando, al romper el día, me despertó una extraña vocecita que decía: —Por favor... ¡dibújame un cordero!",
                    translationText = "Imagine my surprise then when, at daybreak, I was awakened by an odd little voice saying: —Please... draw me a sheep!"
                )
            )
        ),
        Book(
            id = "es-02-don-quijote",
            title = "Don Quijote de la Mancha",
            translatedTitle = "Don Quixote of La Mancha",
            author = "Miguel de Cervantes",
            description = "The immortal adventures of an idealistic knight errant and his faithful squire Sancho Panza battling towering windmills.",
            targetLanguage = Language.SPANISH,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "🛡️",
            coverGradientStart = 0xFF78350F,
            coverGradientEnd = 0xFFB45309,
            coverTheme = BookCoverTheme.DESERT_AMBER,
            totalWords = 380,
            explicitPageCount = 3,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "En un lugar de la Mancha, de cuyo nombre no quiero acordarme, no ha mucho tiempo que vivía un hidalgo de los de lanza en astillero y rocín flaco.",
                    translationText = "In a village of La Mancha, the name of which I do not wish to recall, there lived not long ago a gentleman who kept a lance in the rack and a skinny nag."
                ),
                BilingualParagraph(
                    targetText = "En esto, descubrieron treinta o cuarenta molinos de viento que hay en aquel campo; y así como don Quijote los vio, pensó que eran descomunales gigantes.",
                    translationText = "At this point, they spotted thirty or forty windmills across the plain; and as soon as Don Quixote saw them, he thought they were monstrous giants."
                ),
                BilingualParagraph(
                    targetText = "—Mire vuestra merced —respondió Sancho— que aquellos que allí se parecen no son gigantes, sino molinos de viento, y lo que parecen brazos son las aspas.",
                    translationText = "—Look, your worship —Sancho replied— those you see over there are not giants, but windmills, and what look like arms are their sails."
                )
            )
        ),
        Book(
            id = "es-03-el-dorado",
            title = "La Leyenda de El Dorado",
            translatedTitle = "The Legend of El Dorado",
            author = "Diego Alarcón",
            description = "Explorers venture into the mysterious Amazon rainforest following ancient legends of a city sculpted from pure gold.",
            targetLanguage = Language.SPANISH,
            difficulty = DifficultyLevel.B2,
            coverEmoji = "✨",
            coverGradientStart = 0xFFD4AF37,
            coverGradientEnd = 0xFF78350F,
            coverTheme = BookCoverTheme.DESERT_AMBER,
            totalWords = 310,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Entre la densa niebla de la selva amazónica, las aguas esmeralda de la laguna de Guatavita guardaban celosamente el secreto más codiciado.",
                    translationText = "Amidst the dense fog of the Amazon rainforest, the emerald waters of Lake Guatavita jealously guarded the most coveted secret."
                ),
                BilingualParagraph(
                    targetText = "El cacique muisca se cubría el cuerpo con polvo de oro resplandeciente antes de sumergirse ritualmente en el centro del lago sagrado.",
                    translationText = "The Muisca chieftain covered his body with gleaming gold dust before ritualistically submerging into the center of the sacred lake."
                )
            )
        ),
        Book(
            id = "es-04-barcelona",
            title = "Un Paseo por Barcelona",
            translatedTitle = "A Walk Through Barcelona",
            author = "Elena Ramos",
            description = "A gentle morning stroll through Las Ramblas, the Gothic Quarter, and Gaudí's vibrant architectural masterpieces.",
            targetLanguage = Language.SPANISH,
            difficulty = DifficultyLevel.A1,
            coverEmoji = "🎨",
            coverGradientStart = 0xFF3B82F6,
            coverGradientEnd = 0xFFEC4899,
            coverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
            totalWords = 240,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "El sol brilla en Barcelona. La mañana está fresca y llena de vida. Camine por la famosa calle de Las Ramblas hacia el mar azul.",
                    translationText = "The sun shines in Barcelona. The morning is fresh and full of life. I walk along the famous street of Las Ramblas toward the blue sea."
                ),
                BilingualParagraph(
                    targetText = "En el mercado de La Boquería, compro fruta fresca y un delicioso zumo de naranja. Los colores de las flores son hermosos.",
                    translationText = "In La Boqueria market, I buy fresh fruit and a delicious orange juice. The colors of the flowers are beautiful."
                )
            )
        ),
        Book(
            id = "es-05-la-llorona",
            title = "El Misterio de la Llorona",
            translatedTitle = "The Mystery of La Llorona",
            author = "Mateo Silva",
            description = "A famous Mexican folklore legend told beside moonlit rivers under star-strewn desert skies.",
            targetLanguage = Language.SPANISH,
            difficulty = DifficultyLevel.A2,
            coverEmoji = "🌙",
            coverGradientStart = 0xFF1E1B4B,
            coverGradientEnd = 0xFF4338CA,
            coverTheme = BookCoverTheme.CELESTIAL_INDIGO,
            totalWords = 260,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "En las noches de luna llena, cuando el viento sopla entre los sauces del río, los ancianos del pueblo cuentan la antigua leyenda.",
                    translationText = "On nights with a full moon, when the wind blows through the willows by the river, village elders recount the ancient legend."
                ),
                BilingualParagraph(
                    targetText = "Una figura vestida de blanco flota en la niebla, buscando con tristeza lo que el tiempo y las aguas se llevaron para siempre.",
                    translationText = "A figure dressed in white floats in the fog, sorrowfully seeking what time and the waters took away forever."
                )
            )
        ),
        Book(
            id = "es-06-machu-picchu",
            title = "Secretos de Machu Picchu",
            translatedTitle = "Secrets of Machu Picchu",
            author = "Camila Quispe",
            description = "Ascending the high Andes to explore the enigmatic cloud city built by Inca stone masons.",
            targetLanguage = Language.SPANISH,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "⛰️",
            coverGradientStart = 0xFF065F46,
            coverGradientEnd = 0xFF047857,
            coverTheme = BookCoverTheme.NOBLE_EMERALD,
            totalWords = 280,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Al amanecer, las nubes se abren lentamente sobre los picos verdes de los Andes peruanos, revelando terrazas de piedra milenarias.",
                    translationText = "At dawn, clouds part slowly over the green peaks of the Peruvian Andes, revealing millennial stone terraces."
                ),
                BilingualParagraph(
                    targetText = "Los arquitectos incas cortaron bloques gigantescos con tal precisión que ni una hoja de papel cabe entre sus uniones.",
                    translationText = "Inca architects cut gigantic blocks with such precision that not even a sheet of paper fits between their joints."
                )
            )
        ),
        Book(
            id = "es-07-tapas-madrid",
            title = "Ruta de Tapas en Madrid",
            translatedTitle = "Tapas Trail in Madrid",
            author = "Javier Gómez",
            description = "Discovering the gastronomic delights of Spanish tortilla, jamón ibérico, and churros con chocolate.",
            targetLanguage = Language.SPANISH,
            difficulty = DifficultyLevel.A1,
            coverEmoji = "🥘",
            coverGradientStart = 0xFFB45309,
            coverGradientEnd = 0xFFF59E0B,
            coverTheme = BookCoverTheme.DESERT_AMBER,
            totalWords = 220,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "En la Plaza Mayor de Madrid, el aroma a chocolate caliente y churros recién hechos invita a sentarse en una terraza.",
                    translationText = "In Madrid's Plaza Mayor, the scent of hot chocolate and freshly made churros invites one to sit at a terrace."
                ),
                BilingualParagraph(
                    targetText = "Pedimos una ración de tortilla de patatas jugosa y unas croquetas crujientes mientras charlamos alegremente.",
                    translationText = "We order a serving of juicy potato omelette and crispy croquettes while chatting happily."
                )
            )
        ),
        Book(
            id = "es-08-flamenco-andaluz",
            title = "El Alma del Flamenco",
            translatedTitle = "The Soul of Flamenco",
            author = "Rocío Morales",
            description = "The passionate rhythm of Spanish guitars, clapping hands, and heartfelt cante jondo in Seville.",
            targetLanguage = Language.SPANISH,
            difficulty = DifficultyLevel.B2,
            coverEmoji = "💃",
            coverGradientStart = 0xFF991B1B,
            coverGradientEnd = 0xFFBE123C,
            coverTheme = BookCoverTheme.CRIMSON_RUBY,
            totalWords = 270,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "En un patio cordobés lleno de geranios, el sonido profundo de una guitarra flamenca despierta las emociones más intensas.",
                    translationText = "In a Cordoban courtyard filled with geraniums, the deep sound of a flamenco guitar awakens the most intense emotions."
                ),
                BilingualParagraph(
                    targetText = "El taconeo rítmico y el cante jondo expresan el dolor, la alegría y la pasión transmitida durante siglos en Andalucía.",
                    translationText = "The rhythmic heel-tapping and heartfelt song express the pain, joy, and passion transmitted for centuries in Andalusia."
                )
            )
        ),
        Book(
            id = "es-09-la-isla-tesoro",
            title = "El Galeón Hundido del Caribe",
            translatedTitle = "The Sunken Galleon of the Caribbean",
            author = "Esteban Cruz",
            description = "Deep-sea divers discover a Spanish treasure galleon resting beneath turquoise Caribbean waters.",
            targetLanguage = Language.SPANISH,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "⚓",
            coverGradientStart = 0xFF0284C7,
            coverGradientEnd = 0xFF0F172A,
            coverTheme = BookCoverTheme.CELESTIAL_INDIGO,
            totalWords = 290,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "A treinta metros de profundidad, entre corales multicolores y bancos de peces plateados, descansaba el casco de madera del navío.",
                    translationText = "Thirty meters deep, among multicolor corals and schools of silvery fish, rested the wooden hull of the vessel."
                ),
                BilingualParagraph(
                    targetText = "Un cofre de roble cubierto de algas guardaba doblones de plata y antiguos mapas náuticos trazados en pergamino.",
                    translationText = "An oak chest covered in seaweed preserved silver doubloons and ancient nautical charts drawn on parchment."
                )
            )
        ),
        Book(
            id = "es-10-astronomia-atacama",
            title = "Bajo las Estrellas de Atacama",
            translatedTitle = "Beneath the Stars of Atacama",
            author = "Sofía Valenzuela",
            description = "Astronomers gazing into deep galaxies through the world's most powerful telescopes in the Chilean desert.",
            targetLanguage = Language.SPANISH,
            difficulty = DifficultyLevel.C1,
            coverEmoji = "🔭",
            coverGradientStart = 0xFF1E1B4B,
            coverGradientEnd = 0xFF0F172A,
            coverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
            totalWords = 320,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "El desierto de Atacama posee la atmósfera más limpia y transparente del planeta, una ventana privilegiada hacia el cosmos.",
                    translationText = "The Atacama Desert possesses the cleanest, most transparent atmosphere on the planet, a privileged window into the cosmos."
                ),
                BilingualParagraph(
                    targetText = "Los gigantescos radiotelescopios captan el eco distante de supernovas y nebulosas nacidas en los albores del universo conocido.",
                    translationText = "Gigantic radiotelescopes capture the distant echo of supernovas and nebulas born at the dawn of the known universe."
                )
            )
        ),

        // ==================== 2. FRENCH (Français) - 10 Stories ====================
        Book(
            id = "fr-01-voyageur",
            title = "Le Voyage Mystérieux",
            translatedTitle = "The Mysterious Journey",
            author = "Claire Delacroix",
            description = "An atmospheric journey through the cobblestone streets of Paris and the misty countryside of Brittany.",
            targetLanguage = Language.FRENCH,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "🗼",
            coverGradientStart = 0xFF06B6D4,
            coverGradientEnd = 0xFF3B82F6,
            coverTheme = BookCoverTheme.CELESTIAL_INDIGO,
            totalWords = 320,
            explicitPageCount = 3,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Le train quitta la gare de Montparnasse sous une pluie fine. À travers la vitre embuée, les lumières de Paris s'estompaient doucement.",
                    translationText = "The train left Montparnasse station under a light rain. Through the misted window, the lights of Paris faded gently."
                ),
                BilingualParagraph(
                    targetText = "Julien tenait fermement dans sa poche la vieille lettre jaunie qu'il avait trouvée dans le grenier de son grand-père.",
                    translationText = "Julien held tightly in his pocket the old, yellowed letter he had discovered in his grandfather's attic."
                ),
                BilingualParagraph(
                    targetText = "« Rendez-vous au phare de la Pointe du Raz au coucher du soleil », disait le message mystérieux.",
                    translationText = "« Meet at the Pointe du Raz lighthouse at sunset », read the mysterious message."
                )
            )
        ),
        Book(
            id = "fr-02-renard-secret",
            title = "Le Renard et le Secret",
            translatedTitle = "The Fox and the Secret",
            author = "Antoine de Saint-Exupéry",
            description = "The touching encounter discovering that one sees clearly only with the heart.",
            targetLanguage = Language.FRENCH,
            difficulty = DifficultyLevel.A2,
            coverEmoji = "🦊",
            coverGradientStart = 0xFFB45309,
            coverGradientEnd = 0xFFF59E0B,
            coverTheme = BookCoverTheme.DESERT_AMBER,
            totalWords = 300,
            explicitPageCount = 3,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "C'est alors qu'apparut le renard : — Bonjour, dit le renard. — Bonjour, répondit poliment le petit prince.",
                    translationText = "It was then that the fox appeared: — Good morning, said the fox. — Good morning, politely replied the little prince."
                ),
                BilingualParagraph(
                    targetText = "— Voici mon secret. Il est très simple : on ne voit bien qu'avec le cœur. L'essentiel est invisible pour les yeux.",
                    translationText = "— Here is my secret. It is very simple: it is only with the heart that one can see rightly. What is essential is invisible to the eye."
                )
            )
        ),
        Book(
            id = "fr-03-nuit-louvre",
            title = "Une Nuit au Musée du Louvre",
            translatedTitle = "A Night at the Louvre Museum",
            author = "Lucas Moreau",
            description = "A young student spends an enchanting evening among classical Greek statues and Renaissance portraits.",
            targetLanguage = Language.FRENCH,
            difficulty = DifficultyLevel.A1,
            coverEmoji = "🏛️",
            coverGradientStart = 0xFF1E293B,
            coverGradientEnd = 0xFF475569,
            coverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
            totalWords = 230,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Il est huit heures du soir à Paris. La grande pyramide de verre du Louvre s'illumine d'une douce lumière dorée.",
                    translationText = "It is eight o'clock in the evening in Paris. The great glass pyramid of the Louvre lights up with a soft golden glow."
                ),
                BilingualParagraph(
                    targetText = "Les salles sont silencieuses. Je regarde le mystérieux sourire de la Joconde peint par Léonard de Vinci.",
                    translationText = "The halls are quiet. I look at the mysterious smile of the Mona Lisa painted by Leonardo da Vinci."
                )
            )
        ),
        Book(
            id = "fr-04-vingt-mille-lieues",
            title = "Vingt Mille Lieues sous les Mers",
            translatedTitle = "Twenty Thousand Leagues Under the Sea",
            author = "Jules Verne",
            description = "Captain Nemo and the Nautilus explore the glowing underwater trenches and coral reefs of the oceans.",
            targetLanguage = Language.FRENCH,
            difficulty = DifficultyLevel.B2,
            coverEmoji = "🌊",
            coverGradientStart = 0xFF0F172A,
            coverGradientEnd = 0xFF0284C7,
            coverTheme = BookCoverTheme.CELESTIAL_INDIGO,
            totalWords = 310,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "La mer est tout. Elle couvre les sept dixièmes du globe terrestre. Son souffle est pur et sain.",
                    translationText = "The sea is everything. It covers seven tenths of the terrestrial globe. Its breath is pure and healthy."
                ),
                BilingualParagraph(
                    targetText = "Le Nautilus glissait silencieusement à travers les forêts de coraux phosphorescents, éclairant les profondeurs inconnues.",
                    translationText = "The Nautilus glided silently through forests of phosphorescent corals, illuminating the unknown depths."
                )
            )
        ),
        Book(
            id = "fr-05-boulangerie-provence",
            title = "Le Parfum du Pain en Provence",
            translatedTitle = "The Scent of Bread in Provence",
            author = "Amélie Laurent",
            description = "Baking crusty baguettes and lavender pastries in a sun-drenched stone cottage in southern France.",
            targetLanguage = Language.FRENCH,
            difficulty = DifficultyLevel.A1,
            coverEmoji = "🥖",
            coverGradientStart = 0xFFD97706,
            coverGradientEnd = 0xFFB45309,
            coverTheme = BookCoverTheme.DESERT_AMBER,
            totalWords = 240,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Chaque matin à l'aube, le village provençal se réveille avec l'odeur divine du pain chaud et des croissants dorés.",
                    translationText = "Every morning at dawn, the Provençal village wakes to the divine smell of hot bread and golden croissants."
                ),
                BilingualParagraph(
                    targetText = "Le boulanger pétrit la farine avec amour en écoutant le chant matinal des cigales sous les oliviers centenaires.",
                    translationText = "The baker kneads the flour with love while listening to the morning song of cicadas under ancient olive trees."
                )
            )
        ),
        Book(
            id = "fr-06-le-tour-du-monde",
            title = "Le Tour du Monde en 80 Jours",
            translatedTitle = "Around the World in 80 Days",
            author = "Jules Verne",
            description = "Phileas Fogg and Passepartout embark on a whirlwind wager around the globe across steam trains and packet boats.",
            targetLanguage = Language.FRENCH,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "🌍",
            coverGradientStart = 0xFF047857,
            coverGradientEnd = 0xFF065F46,
            coverTheme = BookCoverTheme.NOBLE_EMERALD,
            totalWords = 300,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Monsieur Phileas Fogg était un membre distingué du Reform Club de Londres, réputé pour son exacte ponctualité.",
                    translationText = "Mr. Phileas Fogg was a distinguished member of London's Reform Club, renowned for his exact punctuality."
                ),
                BilingualParagraph(
                    targetText = "— Je parie vingt mille livres, dit-il calmement, que je ferai le tour de la terre en quatre-vingts jours ou moins.",
                    translationText = "— I wager twenty thousand pounds, he said calmly, that I will make the tour of the earth in eighty days or less."
                )
            )
        ),
        Book(
            id = "fr-07-mont-saint-michel",
            title = "La Merveille de la Baie",
            translatedTitle = "The Wonder of the Bay",
            author = "Henri Bastien",
            description = "The medieval abbey of Mont Saint-Michel rising above the rapid ocean tides of Normandy.",
            targetLanguage = Language.FRENCH,
            difficulty = DifficultyLevel.B2,
            coverEmoji = "🏰",
            coverGradientStart = 0xFF312E81,
            coverGradientEnd = 0xFF4338CA,
            coverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
            totalWords = 280,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Dressé comme un mirage minéral entre ciel et mer, le Mont-Saint-Michel défie les siècles et la force des marées.",
                    translationText = "Standing like a mineral mirage between sky and sea, Mont Saint-Michel defies centuries and the power of tides."
                ),
                BilingualParagraph(
                    targetText = "Les pèlerins traversaient autrefois les sables mouvants guidés par la silhouette imposante de l'archange saint Michel.",
                    translationText = "Pilgrims once crossed shifting quicksands guided by the imposing silhouette of the archangel Saint Michael."
                )
            )
        ),
        Book(
            id = "fr-08-cafe-rive-gauche",
            title = "Un Café sur la Rive Gauche",
            translatedTitle = "A Café on the Left Bank",
            author = "Margot Vaneau",
            description = "Philosophers, poets, and dreamers sharing espresso and jazz in Saint-Germain-des-Prés.",
            targetLanguage = Language.FRENCH,
            difficulty = DifficultyLevel.A2,
            coverEmoji = "☕",
            coverGradientStart = 0xFF4E342E,
            coverGradientEnd = 0xFF3E2723,
            coverTheme = BookCoverTheme.VINTAGE_LEATHER,
            totalWords = 230,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Assise à une petite table ronde en zinc, je regarde les passants défiler le long du boulevard Saint-Germain.",
                    translationText = "Sitting at a small round zinc table, I watch passersby stroll along Boulevard Saint-Germain."
                ),
                BilingualParagraph(
                    targetText = "Dans un carnet en cuir, j'écris des poèmes au son d'un saxophone qui résonne au loin dans une cave de jazz.",
                    translationText = "In a leather notebook, I write poems to the sound of a saxophone echoing in the distance from a jazz club."
                )
            )
        ),
        Book(
            id = "fr-09-la-foret-broceliande",
            title = "Les Légendes de Brocéliande",
            translatedTitle = "Legends of Broceliande",
            author = "Yvon Le Gall",
            description = "Merlin the Enchanter and the Lady of the Lake in the enchanted fairy forest of Brittany.",
            targetLanguage = Language.FRENCH,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "🧙‍♂️",
            coverGradientStart = 0xFF064E3B,
            coverGradientEnd = 0xFF047857,
            coverTheme = BookCoverTheme.NOBLE_EMERALD,
            totalWords = 270,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Au cœur des chênes moussus de Brocéliande, la fontaine de Barenton bouillonne sans que son eau ne soit jamais chaude.",
                    translationText = "At the heart of the mossy oaks of Broceliande, the fountain of Barenton bubbles without its water ever being warm."
                ),
                BilingualParagraph(
                    targetText = "On raconte que l'enchanteur Merlin y dort encore d'un sommeil magique sous une prison d'air invisible.",
                    translationText = "They say the wizard Merlin still sleeps there in a magical slumber within an invisible prison of air."
                )
            )
        ),
        Book(
            id = "fr-10-alpes-chamonix",
            title = "Sommets des Alpes Blanches",
            translatedTitle = "Peaks of the White Alps",
            author = "Guillaume Favre",
            description = "Climbing glacier ridges toward the breathtaking summit of Mont Blanc under crystal blue skies.",
            targetLanguage = Language.FRENCH,
            difficulty = DifficultyLevel.C1,
            coverEmoji = "🏔️",
            coverGradientStart = 0xFF0F172A,
            coverGradientEnd = 0xFF0284C7,
            coverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
            totalWords = 310,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "À plus de quatre mille mètres d'altitude, le silence glacial de la haute montagne n'est troublé que par le crissement des crampons sur la glace vive.",
                    translationText = "At over four thousand meters above sea level, the icy silence of the high mountain is disturbed only by the crunch of crampons on pure ice."
                ),
                BilingualParagraph(
                    targetText = "La vue sur les séracs étincelants et les vallées embrumées offre une contemplation vertigineuse de la splendeur alpine.",
                    translationText = "The view of sparkling seracs and misty valleys offers a breathtaking contemplation of alpine splendor."
                )
            )
        ),

        // ==================== 3. GERMAN (Deutsch) - 6 Stories ====================
        Book(
            id = "de-01-zauberwald",
            title = "Die Legende vom Zauberwald",
            translatedTitle = "The Legend of the Magic Forest",
            author = "Hermann Keller",
            description = "A mythical German fairy tale about a brave woodcutter's daughter in the Black Forest.",
            targetLanguage = Language.GERMAN,
            difficulty = DifficultyLevel.A2,
            coverEmoji = "🌲",
            coverGradientStart = 0xFF064E3B,
            coverGradientEnd = 0xFF047857,
            coverTheme = BookCoverTheme.NOBLE_EMERALD,
            totalWords = 280,
            explicitPageCount = 3,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Tief im Schwarzwald, wo die Tannen so hoch in den Himmel ragen, lag ein kleines, friedliches Dorf.",
                    translationText = "Deep in the Black Forest, where the fir trees reach high into the sky, lay a small, peaceful village."
                ),
                BilingualParagraph(
                    targetText = "Hannah fand am Waldrand einen glänzenden goldenen Schlüssel, der halb unter einer uralten Eichenwurzel verborgen war.",
                    translationText = "Hannah found a gleaming golden key at the edge of the forest, half-hidden beneath an ancient oak root."
                )
            )
        ),
        Book(
            id = "de-02-rattenfaenger",
            title = "Der Rattenfänger von Hameln",
            translatedTitle = "The Pied Piper of Hamelin",
            author = "Brüder Grimm",
            description = "The folklore legend of the enigmatic piper whose flute possessed power over nature.",
            targetLanguage = Language.GERMAN,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "🎶",
            coverGradientStart = 0xFF7F1D1D,
            coverGradientEnd = 0xFF991B1B,
            coverTheme = BookCoverTheme.CRIMSON_RUBY,
            totalWords = 320,
            explicitPageCount = 3,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Im Jahre 1284 erschien zu Hameln ein wunderlicher Mann. Er zog ein Pfeifchen aus der Tasche und begann zu spielen.",
                    translationText = "In the year 1284, a strange man appeared in Hamelin. He drew a pipe from his pocket and began to play."
                ),
                BilingualParagraph(
                    targetText = "Alsbald kamen die Ratten und Mäuse aus allen Häusern und folgten der wundersamen Melodie bis in die Weser.",
                    translationText = "Immediately, the rats and mice came from all houses and followed the wondrous melody into the river Weser."
                )
            )
        ),
        Book(
            id = "de-03-berlin-morgen",
            title = "Ein Morgen in Berlin",
            translatedTitle = "A Morning in Berlin",
            author = "Max Richter",
            description = "Coffee, historic monuments, and cycling along the Spree river on a crisp morning.",
            targetLanguage = Language.GERMAN,
            difficulty = DifficultyLevel.A1,
            coverEmoji = "🥨",
            coverGradientStart = 0xFF3B82F6,
            coverGradientEnd = 0xFF10B981,
            coverTheme = BookCoverTheme.NOBLE_EMERALD,
            totalWords = 220,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Guten Morgen, Berlin! Die Sonne geht über dem Brandenburger Tor auf. Die Luft ist frisch und klar.",
                    translationText = "Good morning, Berlin! The sun rises over the Brandenburg Gate. The air is fresh and clear."
                ),
                BilingualParagraph(
                    targetText = "Ich trinke einen warmen Kaffee in einer Bäckerei und spaziere gemütlich an der Spree entlang zur Museumsinsel.",
                    translationText = "I drink a warm coffee in a bakery and stroll leisurely along the Spree river toward Museum Island."
                )
            )
        ),
        Book(
            id = "de-04-faust",
            title = "Fausts Streben",
            translatedTitle = "Faust's Quest",
            author = "Johann Wolfgang von Goethe",
            description = "The philosophical tragedy of a scholar yearning to understand the hidden mysteries of existence.",
            targetLanguage = Language.GERMAN,
            difficulty = DifficultyLevel.B2,
            coverEmoji = "📜",
            coverGradientStart = 0xFF3E2723,
            coverGradientEnd = 0xFF4E342E,
            coverTheme = BookCoverTheme.VINTAGE_LEATHER,
            totalWords = 290,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Habe nun, ach! Philosophie, Juristerei und Medizin durchaus studiert, mit heißem Bemühn.",
                    translationText = "I have now, alas! studied philosophy, jurisprudence, and medicine, with ardent effort."
                ),
                BilingualParagraph(
                    targetText = "Dass ich erkenne, was die Welt im Innersten zusammenhält, schau alle Wirkenskraft und Samen.",
                    translationText = "That I may discern what holds the world together in its inmost core, gaze upon all creative power and seeds."
                )
            )
        ),
        Book(
            id = "de-05-neuschwanstein",
            title = "Das Märchenschloss in Bayern",
            translatedTitle = "The Fairy Tale Castle in Bavaria",
            author = "Katharina Weber",
            description = "King Ludwig II's romantic white castle nestled amongst snow-capped Bavarian Alps.",
            targetLanguage = Language.GERMAN,
            difficulty = DifficultyLevel.A2,
            coverEmoji = "🏰",
            coverGradientStart = 0xFF1E293B,
            coverGradientEnd = 0xFF0284C7,
            coverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
            totalWords = 240,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Hoch oben auf einem felsigen Hügel über dem Alpsee thront das prachtvolle Schloss Neuschwanstein.",
                    translationText = "High upon a rocky hill above the Alpsee lake reigns the magnificent Neuschwanstein Castle."
                ),
                BilingualParagraph(
                    targetText = "König Ludwig II. ließ diesen Traum aus weißem Kalkstein erbauen, inspiriert von alten germanischen Ritteropern.",
                    translationText = "King Ludwig II had this dream built of white limestone, inspired by ancient Germanic knightly operas."
                )
            )
        ),
        Book(
            id = "de-06-albert-einstein",
            title = "Einsteins Gedankenreise",
            translatedTitle = "Einstein's Thought Journey",
            author = "Dr. Stefan Meier",
            description = "A young patent clerk imagining riding a beam of light across time and space in Bern.",
            targetLanguage = Language.GERMAN,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "💡",
            coverGradientStart = 0xFF4F46E5,
            coverGradientEnd = 0xFF9333EA,
            coverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
            totalWords = 270,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Der junge Albert saß an seinem Schreibtisch und fragte sich: Was würde passieren, wenn man auf einem Lichtstrahl reitet?",
                    translationText = "Young Albert sat at his desk and wondered: What would happen if one could ride along on a beam of light?"
                ),
                BilingualParagraph(
                    targetText = "Mit einfachen Gedankenexperimenten revolutionierte er unser Verständnis von Raum, Zeit und Gravitation.",
                    translationText = "With simple thought experiments, he revolutionized our understanding of space, time, and gravity."
                )
            )
        )
    )
}
