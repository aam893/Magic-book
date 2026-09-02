package com.example.data.sample

import com.example.data.model.BilingualParagraph
import com.example.data.model.Book
import com.example.data.model.BookCoverTheme
import com.example.data.model.DifficultyLevel
import com.example.data.model.Language

object SampleBooksPart3 {
    val list: List<Book> = listOf(
        // ==================== 6. ITALIAN (Italiano) - 10 Stories ====================
        Book(
            id = "it-01-segreti-roma",
            title = "I Segreti di Roma Antica",
            translatedTitle = "The Secrets of Ancient Rome",
            author = "Marco Bellini",
            description = "Uncovering hidden chambers beneath the Colosseum and forgotten cobblestones of the Roman Forum.",
            targetLanguage = Language.ITALIAN,
            difficulty = DifficultyLevel.A2,
            coverEmoji = "🏛️",
            coverGradientStart = 0xFF78350F,
            coverGradientEnd = 0xFFB45309,
            coverTheme = BookCoverTheme.DESERT_AMBER,
            totalWords = 280,
            explicitPageCount = 3,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Camminare per le strade di Roma è come sfogliare le pagine dorate di un libro di storia a cielo aperto.",
                    translationText = "Walking the streets of Rome is like turning the golden pages of an open-air history book."
                ),
                BilingualParagraph(
                    targetText = "Sotto i vicoli di Trastevere, gli archeologi hanno scoperto mosaici colorati rimasti intatti per duemila anni.",
                    translationText = "Beneath the alleyways of Trastevere, archaeologists discovered colorful mosaics intact for two thousand years."
                )
            )
        ),
        Book(
            id = "it-02-divina-commedia",
            title = "La Divina Commedia: La Selva Oscura",
            translatedTitle = "The Divine Comedy: The Dark Forest",
            author = "Dante Alighieri",
            description = "Dante's immortal journey through the dark wood guided by the spirit of the Roman poet Virgil.",
            targetLanguage = Language.ITALIAN,
            difficulty = DifficultyLevel.B2,
            coverEmoji = "🌲",
            coverGradientStart = 0xFF064E3B,
            coverGradientEnd = 0xFF047857,
            coverTheme = BookCoverTheme.NOBLE_EMERALD,
            totalWords = 310,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Nel mezzo del cammin di nostra vita mi ritrovai per una selva oscura, ché la diritta via era smarrita.",
                    translationText = "Midway upon the journey of our life I found myself within a dark forest, for the straightforward pathway had been lost."
                ),
                BilingualParagraph(
                    targetText = "Ahi quanto a dir qual era è cosa dura esta selva selvaggia e aspra e forte che nel pensier rinova la paura!",
                    translationText = "Ah, how hard a thing it is to say what that forest was, so wild, rugged, and harsh, the very thought of which renews the fear!"
                )
            )
        ),
        Book(
            id = "it-03-notte-venezia",
            title = "Una Notte a Venezia",
            translatedTitle = "A Night in Venice",
            author = "Chiara Rossi",
            description = "Gondolas gliding along misty canals under the Rialto Bridge and San Marco square.",
            targetLanguage = Language.ITALIAN,
            difficulty = DifficultyLevel.A1,
            coverEmoji = "🎭",
            coverGradientStart = 0xFF0284C7,
            coverGradientEnd = 0xFF0F172A,
            coverTheme = BookCoverTheme.CELESTIAL_INDIGO,
            totalWords = 220,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "La gondola scivola silenziosa sulle acque scure del Canal Grande. I lampioni illuminano gli antichi palazzi d'oro.",
                    translationText = "The gondola glides quietly along the dark waters of the Grand Canal. Streetlamps illuminate ancient golden palaces."
                ),
                BilingualParagraph(
                    targetText = "In Piazza San Marco, un violinista suona una dolce melodia mentre la luna risplende sulla laguna.",
                    translationText = "In St. Mark's Square, a violinist plays a sweet melody while the moon shines over the lagoon."
                )
            )
        ),
        Book(
            id = "it-04-leonardo-da-vinci",
            title = "Il Taccuino di Leonardo",
            translatedTitle = "Leonardo's Notebook",
            author = "Giovanni Moretti",
            description = "Flying machines, anatomical sketches, and timeless genius in Renaissance Florence.",
            targetLanguage = Language.ITALIAN,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "🎨",
            coverGradientStart = 0xFF3E2723,
            coverGradientEnd = 0xFF78350F,
            coverTheme = BookCoverTheme.VINTAGE_LEATHER,
            totalWords = 270,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Nella sua bottega di Firenze, Leonardo disegnava con inchiostro di noce ali di pipistrello e ingranaggi complessi.",
                    translationText = "In his Florence workshop, Leonardo drew with walnut ink bat wings and complex mechanical gears."
                ),
                BilingualParagraph(
                    targetText = "«La sapienza è figliola della sperienza», amava ripetere mentre osservava il volo degli uccelli sulle colline toscane.",
                    translationText = "«Wisdom is the daughter of experience», he loved to repeat while observing birds in flight over Tuscan hills."
                )
            )
        ),
        Book(
            id = "it-05-pizza-napoli",
            title = "L'Arte della Vera Pizza Napoletana",
            translatedTitle = "The Art of True Neapolitan Pizza",
            author = "Salvatore Esposito",
            description = "Wood-fired ovens, San Marzano tomatoes, and fresh buffalo mozzarella in Naples.",
            targetLanguage = Language.ITALIAN,
            difficulty = DifficultyLevel.A1,
            coverEmoji = "🍕",
            coverGradientStart = 0xFFDC2626,
            coverGradientEnd = 0xFF991B1B,
            coverTheme = BookCoverTheme.CRIMSON_RUBY,
            totalWords = 210,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "A Napoli, la pizza è una vera passione d'arte! L'impasto lievita lentamente per ventiquattro ore.",
                    translationText = "In Naples, pizza is a true artistic passion! The dough rises slowly for twenty-four hours."
                ),
                BilingualParagraph(
                    targetText = "Nel forno a legna a quattrocento gradi, la Margherita cuoce in soli novanta secondi con basilico fresco.",
                    translationText = "In the wood-fired oven at four hundred degrees, the Margherita bakes in just ninety seconds with fresh basil."
                )
            )
        ),
        Book(
            id = "it-06-pinocchio",
            title = "Le Avventure di Pinocchio",
            translatedTitle = "The Adventures of Pinocchio",
            author = "Carlo Collodi",
            description = "Geppetto carves a wooden puppet that comes alive and dreams of becoming a real boy.",
            targetLanguage = Language.ITALIAN,
            difficulty = DifficultyLevel.A2,
            coverEmoji = "🪵",
            coverGradientStart = 0xFFB45309,
            coverGradientEnd = 0xFF78350F,
            coverTheme = BookCoverTheme.DESERT_AMBER,
            totalWords = 250,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "C'era una volta... — Un re! — diranno subito i miei piccoli lettori. No, ragazzi, avete sbagliato. C'era una volta un pezzo di legno.",
                    translationText = "Once upon a time there was... — A king! — my little readers will instantly say. No, children, you are mistaken. Once upon a time there was a piece of wood."
                ),
                BilingualParagraph(
                    targetText = "Mastro Geppetto lo intagliò con cura per farne un burattino che sapesse ballare e fare salti mortali.",
                    translationText = "Master Geppetto carved it with care to make a marionette that could dance and do somersaults."
                )
            )
        ),
        Book(
            id = "it-07-costiera-amalfitana",
            title = "I Limoni di Amalfi",
            translatedTitle = "The Lemons of Amalfi",
            author = "Laura De Luca",
            description = "Sunlit cliffs, pastel villas, and azure Mediterranean waves along the Amalfi coast.",
            targetLanguage = Language.ITALIAN,
            difficulty = DifficultyLevel.A2,
            coverEmoji = "🍋",
            coverGradientStart = 0xFFD97706,
            coverGradientEnd = 0xFF047857,
            coverTheme = BookCoverTheme.NOBLE_EMERALD,
            totalWords = 230,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Sulle ripide scogliere a picco sul mare blu, i giardini di limoni profumano l'aria di freschezza estiva.",
                    translationText = "On steep cliffs overlooking the blue sea, lemon groves scent the air with summer freshness."
                ),
                BilingualParagraph(
                    targetText = "Le case color pastello si arrampicano sulla montagna creando un panorama da cartolina indimenticabile.",
                    translationText = "Pastel-colored houses climb the mountainside creating an unforgettable postcard panorama."
                )
            )
        ),
        Book(
            id = "it-08-il-gattopardo",
            title = "Il Gattopardo: Luce di Sicilia",
            translatedTitle = "The Leopard: Light of Sicily",
            author = "Giuseppe Tomasi di Lampedusa",
            description = "Noble traditions confronting modern change in the sun-drenched landscapes of Sicily.",
            targetLanguage = Language.ITALIAN,
            difficulty = DifficultyLevel.C1,
            coverEmoji = "🐆",
            coverGradientStart = 0xFF78350F,
            coverGradientEnd = 0xFF3E2723,
            coverTheme = BookCoverTheme.VINTAGE_LEATHER,
            totalWords = 300,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "«Se vogliamo che tutto rimanga come è, bisogna che tutto cambi», sussurrò Tancredi con sorriso complice.",
                    translationText = "«If we want everything to remain as it is, everything must change», Tancredi whispered with an understanding smile."
                ),
                BilingualParagraph(
                    targetText = "La Sicilia sonnecchiava sotto il sole implacabile, custodendo il fascino aristocratico di un'epoca al tramonto.",
                    translationText = "Sicily dozed beneath the relentless sun, guarding the aristocratic charm of an era at its dusk."
                )
            )
        ),
        Book(
            id = "it-09-vesuvio-pompei",
            title = "L'Eco Sospesa di Pompei",
            translatedTitle = "The Suspended Echo of Pompeii",
            author = "Antonio Ferri",
            description = "Rediscovering Roman bakery ovens, villa frescoes, and cobblestone crossroads preserved under volcanic ash.",
            targetLanguage = Language.ITALIAN,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "🌋",
            coverGradientStart = 0xFF991B1B,
            coverGradientEnd = 0xFF4338CA,
            coverTheme = BookCoverTheme.CRIMSON_RUBY,
            totalWords = 270,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "La cenere del Vesuvio ha fermato il tempo in un giorno d'autunno del settantanove dopo Cristo.",
                    translationText = "The ash of Vesuvius stopped time on an autumn day in the seventy-ninth year after Christ."
                ),
                BilingualParagraph(
                    targetText = "Passeggiando tra le terme e i teatri antichi, si può quasi ascoltare la voce viva degli artigiani romani.",
                    translationText = "Strolling through ancient baths and theaters, one can almost hear the living voices of Roman artisans."
                )
            )
        ),
        Book(
            id = "it-10-gelato-firenze",
            title = "La Dolce Vita del Gelato",
            translatedTitle = "The Sweet Life of Gelato",
            author = "Beatrice Conti",
            description = "Pistachio, dark chocolate, and stracciatella crafted with fresh cream in Florence.",
            targetLanguage = Language.ITALIAN,
            difficulty = DifficultyLevel.A1,
            coverEmoji = "🍨",
            coverGradientStart = 0xFFEC4899,
            coverGradientEnd = 0xFF3B82F6,
            coverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
            totalWords = 200,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "In una calda giornata d'estate, niente è più delizioso di un cono di gelato artigianale al pistacchio di Bronte.",
                    translationText = "On a warm summer day, nothing is more delicious than an artisanal pistachio gelato cone from Bronte."
                ),
                BilingualParagraph(
                    targetText = "Seduti vicino al Ponte Vecchio, gustiamo ogni cucchiaio ammirando i riflessi d'oro sul fiume Arno.",
                    translationText = "Sitting near Ponte Vecchio, we savor every spoonful admiring the golden reflections on the Arno river."
                )
            )
        ),

        // ==================== 7. PORTUGUESE (Português) - 10 Stories ====================
        Book(
            id = "pt-01-lusiadas",
            title = "Os Lusíadas e o Mar Desconhecido",
            translatedTitle = "The Lusiads and the Unknown Sea",
            author = "Luís de Camões",
            description = "Portuguese navigators rounding the Cape of Good Hope to map new maritime horizons.",
            targetLanguage = Language.PORTUGUESE,
            difficulty = DifficultyLevel.B2,
            coverEmoji = "⛵",
            coverGradientStart = 0xFF0284C7,
            coverGradientEnd = 0xFF065F46,
            coverTheme = BookCoverTheme.NOBLE_EMERALD,
            totalWords = 300,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "As armas e os barões assinalados, que da ocidental praia Lusitana, por mares nunca dantes navegados passaram.",
                    translationText = "Arms and the illustrious barons who, from the western Lusitanian shore, sailed across seas never navigated before."
                ),
                BilingualParagraph(
                    targetText = "Desafiaram as tempestades do Adamastor e abriram novos caminhos para o Oriente com bravura lendária.",
                    translationText = "They defied Adamastor's storms and opened new routes to the East with legendary bravery."
                )
            )
        ),
        Book(
            id = "pt-02-galo-barcelos",
            title = "A Lenda do Galo de Barcelos",
            translatedTitle = "The Legend of the Barcelos Rooster",
            author = "Folclore Tradicional",
            description = "The famous Portuguese symbol of faith, justice, and good luck.",
            targetLanguage = Language.PORTUGUESE,
            difficulty = DifficultyLevel.A2,
            coverEmoji = "🐓",
            coverGradientStart = 0xFFDC2626,
            coverGradientEnd = 0xFFD97706,
            coverTheme = BookCoverTheme.CRIMSON_RUBY,
            totalWords = 260,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Um jovem peregrino galego foi injustamente acusado de um crime na pacata vila de Barcelos.",
                    translationText = "A young Galician pilgrim was unjustly accused of a crime in the quiet town of Barcelos."
                ),
                BilingualParagraph(
                    targetText = "Ele apontou para o galo assado na mesa do juiz e disse: «Se sou inocente, este galo cantará!». E o galo cantou!",
                    translationText = "He pointed to the roasted rooster on the judge's table and said: «If I am innocent, this rooster will crow!». And the rooster crowed!"
                )
            )
        ),
        Book(
            id = "pt-03-tarde-lisboa",
            title = "Uma Tarde nos Miradouros de Lisboa",
            translatedTitle = "An Afternoon in the Viewpoints of Lisbon",
            author = "Rodrigo Fontes",
            description = "Yellow vintage trams, fado guitar chords, and pastel de nata in Alfama.",
            targetLanguage = Language.PORTUGUESE,
            difficulty = DifficultyLevel.A1,
            coverEmoji = "🚋",
            coverGradientStart = 0xFFD97706,
            coverGradientEnd = 0xFF3B82F6,
            coverTheme = BookCoverTheme.DESERT_AMBER,
            totalWords = 220,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "O elétrico vinte e oito sobe as colinas estreitas de Alfama enquanto o sol brilha sobre o rio Tejo.",
                    translationText = "The number twenty-eight tram climbs the narrow hills of Alfama while the sun shines over the Tagus river."
                ),
                BilingualParagraph(
                    targetText = "No miradouro de Santa Luzia, saboreamos um pastel de nata quente polvilhado com canela e açúcar.",
                    translationText = "At the Santa Luzia viewpoint, we savor a warm pastel de nata sprinkled with cinnamon and sugar."
                )
            )
        ),
        Book(
            id = "pt-04-rio-janeiro-samba",
            title = "O Ritmo Alegre do Rio de Janeiro",
            translatedTitle = "The Joyful Rhythm of Rio de Janeiro",
            author = "Mariana Costa",
            description = "Bossa nova beats, golden sands of Ipanema, and Christ the Redeemer embracing the bay.",
            targetLanguage = Language.PORTUGUESE,
            difficulty = DifficultyLevel.A2,
            coverEmoji = "🌴",
            coverGradientStart = 0xFF047857,
            coverGradientEnd = 0xFF065F46,
            coverTheme = BookCoverTheme.NOBLE_EMERALD,
            totalWords = 240,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "No final da tarde na praia de Copacabana, o som do violão toca notas suaves de bossa nova sob as palmeiras.",
                    translationText = "In the late afternoon on Copacabana beach, the sound of the acoustic guitar plays gentle bossa nova notes under palm trees."
                ),
                BilingualParagraph(
                    targetText = "O Cristo Redentor no topo do Corcovado abençoa a cidade maravilhosa com braços abertos ao horizonte.",
                    translationText = "Christ the Redeemer atop Corcovado blesses the marvelous city with arms open to the horizon."
                )
            )
        ),
        Book(
            id = "pt-05-floresta-amazonica",
            title = "O Canto da Floresta Amazônica",
            translatedTitle = "The Song of the Amazon Rainforest",
            author = "Tiago Silva",
            description = "Navigating mighty rivers beneath lush green canopies home to vibrant toucans and jaguars.",
            targetLanguage = Language.PORTUGUESE,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "🦜",
            coverGradientStart = 0xFF064E3B,
            coverGradientEnd = 0xFF047857,
            coverTheme = BookCoverTheme.NOBLE_EMERALD,
            totalWords = 270,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "O encontro das águas escuras do Rio Negro com as águas barrentas do Solimões corre lado a lado sem se misturar.",
                    translationText = "The meeting of the dark waters of the Rio Negro with the muddy waters of the Solimões runs side by side without mixing."
                ),
                BilingualParagraph(
                    targetText = "A copa das árvores gigantescas abriga araras coloridas e plantas medicinais de valor inestimável.",
                    translationText = "The canopy of gigantic trees shelters colorful macaws and medicinal plants of invaluable worth."
                )
            )
        ),
        Book(
            id = "pt-06-fernando-pessoa",
            title = "O Livro do Desassossego",
            translatedTitle = "The Book of Disquiet",
            author = "Fernando Pessoa (Bernardo Soares)",
            description = "Poetic, introspective meditations on dreaming, consciousness, and Lisbon rainy streets.",
            targetLanguage = Language.PORTUGUESE,
            difficulty = DifficultyLevel.C1,
            coverEmoji = "🌧️",
            coverGradientStart = 0xFF312E81,
            coverGradientEnd = 0xFF1E1B4B,
            coverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
            totalWords = 290,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "«Tenho em mim todos os sonhos do mundo», escreveu Pessoa na quietude do seu quarto na Baixa pombalina.",
                    translationText = "«I have in me all the dreams of the world», wrote Pessoa in the quiet of his room in the Pombaline Baixa."
                ),
                BilingualParagraph(
                    targetText = "A chuva que cai lá fora lava as pedras da calçada portuguesa com uma melancolia suave e doce.",
                    translationText = "The rain falling outside washes the stones of Portuguese pavement with a soft and gentle melancholy."
                )
            )
        ),
        Book(
            id = "pt-07-porto-vinho",
            title = "As Caves do Vinho do Porto",
            translatedTitle = "The Port Wine Cellars",
            author = "Afonso Moreira",
            description = "Barcos rabelos floating on the Douro river beneath the iconic Dom Luís I iron bridge.",
            targetLanguage = Language.PORTUGUESE,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "🍷",
            coverGradientStart = 0xFF78350F,
            coverGradientEnd = 0xFF991B1B,
            coverTheme = BookCoverTheme.CRIMSON_RUBY,
            totalWords = 250,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Os socalcos das vinhas do Alto Douro descem majestosos até à margem do rio onde os barcos rabelos descansam.",
                    translationText = "The terraced vineyards of the Upper Douro descend majestically to the riverbank where rabelo boats rest."
                ),
                BilingualParagraph(
                    targetText = "Nas caves centenárias de Gaia, os tonéis de carvalho envelhecem o vinho doce que viaja pelo mundo inteiro.",
                    translationText = "In the centuries-old cellars of Gaia, oak casks age the sweet wine that travels across the entire world."
                )
            )
        ),
        Book(
            id = "pt-08-fado-coimbra",
            title = "A Serenata dos Estudantes de Coimbra",
            translatedTitle = "The Coimbra Students' Serenade",
            author = "Inês Guimarães",
            description = "Black academic capes and melancholy Portuguese guitars echoing on the steps of the Old Cathedral.",
            targetLanguage = Language.PORTUGUESE,
            difficulty = DifficultyLevel.B2,
            coverEmoji = "🎸",
            coverGradientStart = 0xFF3E2723,
            coverGradientEnd = 0xFF1E1B4B,
            coverTheme = BookCoverTheme.VINTAGE_LEATHER,
            totalWords = 260,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "À meia-noite na Sé Velha, os estudantes trajados de capa e batina negra cantam a saudade dos anos de juventude.",
                    translationText = "At midnight by the Old Cathedral, students in black academic capes sing of longing for the years of youth."
                ),
                BilingualParagraph(
                    targetText = "O som cristalino da guitarra de Coimbra toca a alma de quem ouve com uma reverência comovente.",
                    translationText = "The crystalline sound of the Coimbra guitar touches the listener's soul with moving reverence."
                )
            )
        ),
        Book(
            id = "pt-09-capoeira-bahia",
            title = "A Roda de Capoeira em Salvador",
            translatedTitle = "The Capoeira Circle in Salvador",
            author = "Mestre Damião",
            description = "Berimbau rhythms, acrobatic martial arts, and Afro-Brazilian heritage in Bahia.",
            targetLanguage = Language.PORTUGUESE,
            difficulty = DifficultyLevel.A2,
            coverEmoji = "🪘",
            coverGradientStart = 0xFFD97706,
            coverGradientEnd = 0xFFB45309,
            coverTheme = BookCoverTheme.DESERT_AMBER,
            totalWords = 230,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "No Pelourinho, o toque do berimbau e as palmas sincronizadas convidam os jogadores a entrar na roda.",
                    translationText = "In Pelourinho, the rhythm of the berimbau and synchronized clapping invite the players into the circle."
                ),
                BilingualParagraph(
                    targetText = "A capoeira mistura dança, luta e poesia em movimentos ágeis cheios de ancestralidade e força.",
                    translationText = "Capoeira blends dance, fight, and poetry in agile movements full of ancestral roots and power."
                )
            )
        ),
        Book(
            id = "pt-10-ilha-madeira",
            title = "O Jardim Flutuante da Madeira",
            translatedTitle = "The Floating Garden of Madeira",
            author = "Cláudia Pestana",
            description = "Lush levada waterways, dramatic cliffs, and tropical flower gardens in the Atlantic.",
            targetLanguage = Language.PORTUGUESE,
            difficulty = DifficultyLevel.A1,
            coverEmoji = "🌺",
            coverGradientStart = 0xFF047857,
            coverGradientEnd = 0xFF0284C7,
            coverTheme = BookCoverTheme.NOBLE_EMERALD,
            totalWords = 210,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "A ilha da Madeira é um verdadeiro paraíso no oceano com flores exóticas de todas as cores imagináveis.",
                    translationText = "Madeira Island is a true paradise in the ocean with exotic flowers of every imaginable color."
                ),
                BilingualParagraph(
                    targetText = "Caminhar pelas levadas entre cascatas cristalinas renova a energia e traz paz ao coração.",
                    translationText = "Walking along the levada canals between crystalline waterfalls renews energy and brings peace to the heart."
                )
            )
        ),

        // ==================== 8. ENGLISH (English) - 10 Stories ====================
        Book(
            id = "en-01-sherlock-holmes",
            title = "The Blue Carbuncle",
            translatedTitle = "The Blue Carbuncle",
            author = "Sir Arthur Conan Doyle",
            description = "Sherlock Holmes and Dr. Watson investigate an extraordinary jewel discovered inside a Christmas goose.",
            targetLanguage = Language.ENGLISH,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "🔍",
            coverGradientStart = 0xFF1E293B,
            coverGradientEnd = 0xFF0F172A,
            coverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
            totalWords = 310,
            explicitPageCount = 3,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "I had called upon my friend Sherlock Holmes upon the second morning after Christmas, with the intention of wishing him the compliments of the season.",
                    translationText = "J'avais rendu visite à mon ami Sherlock Holmes le deuxième matin après Noël, dans l'intention de lui présenter mes vœux."
                ),
                BilingualParagraph(
                    targetText = "He was lounging upon the sofa in a purple dressing-gown, a pipe-rack within his reach upon the right.",
                    translationText = "Il était affalé sur le canapé dans une robe de chambre violette, un râtelier de pipes à portée de main sur sa droite."
                ),
                BilingualParagraph(
                    targetText = "«You see, Watson,» said Holmes, «the singular facts which have been brought to our knowledge point toward a most remarkable little mystery.»",
                    translationText = "«Vous voyez, Watson,» dit Holmes, «les faits singuliers portés à notre connaissance pointent vers un bien remarquable petit mystère.»"
                )
            )
        ),
        Book(
            id = "en-02-alice-wonderland",
            title = "Down the Rabbit Hole",
            translatedTitle = "Down the Rabbit Hole",
            author = "Lewis Carroll",
            description = "Alice follows a White Rabbit wearing a waistcoat into a whimsical subterranean wonderland.",
            targetLanguage = Language.ENGLISH,
            difficulty = DifficultyLevel.A2,
            coverEmoji = "🐇",
            coverGradientStart = 0xFF4F46E5,
            coverGradientEnd = 0xFFEC4899,
            coverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
            totalWords = 280,
            explicitPageCount = 3,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "Alice was beginning to get very tired of sitting by her sister on the bank, and of having nothing to do.",
                    translationText = "Alice commençait à se lasser sérieusement d'être assise près de sa sœur sur la berge sans rien avoir à faire."
                ),
                BilingualParagraph(
                    targetText = "Suddenly a White Rabbit with pink eyes ran close by her, took a watch out of its waistcoat-pocket, and hurried on.",
                    translationText = "Soudain, un Lapin Blanc aux yeux roses passa en courant près d'elle, sortit une montre de sa poche de gilet et se dépêcha."
                )
            )
        ),
        Book(
            id = "en-03-treasure-island",
            title = "The Map of Skeleton Island",
            translatedTitle = "The Map of Skeleton Island",
            author = "Robert Louis Stevenson",
            description = "Jim Hawkins discovers a parchment map with red crosses marking Captain Flint's buried pirate gold.",
            targetLanguage = Language.ENGLISH,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "🏴‍☠️",
            coverGradientStart = 0xFF78350F,
            coverGradientEnd = 0xFFB45309,
            coverTheme = BookCoverTheme.DESERT_AMBER,
            totalWords = 270,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "I broke the seal with trembling fingers and drew out a coarse sheet of paper, yellowed with sea salt and age.",
                    translationText = "Je brisai le sceau d'un doigt tremblant et en sortis une grossière feuille de papier jaunie par le sel de mer et l'âge."
                ),
                BilingualParagraph(
                    targetText = "There were three crosses of red ink, and against the largest: 'Bulk of treasure here'.",
                    translationText = "Il y avait trois croix à l'encre rouge, et à côté de la plus grande : 'Gros du trésor ici'."
                )
            )
        ),
        Book(
            id = "en-04-great-gatsby",
            title = "The Green Light on the Bay",
            translatedTitle = "The Green Light on the Bay",
            author = "F. Scott Fitzgerald",
            description = "Jay Gatsby gazing across the dark water at the enigmatic glowing beacon in Long Island.",
            targetLanguage = Language.ENGLISH,
            difficulty = DifficultyLevel.B2,
            coverEmoji = "🍸",
            coverGradientStart = 0xFF064E3B,
            coverGradientEnd = 0xFF047857,
            coverTheme = BookCoverTheme.NOBLE_EMERALD,
            totalWords = 290,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "He stretched out his arms toward the dark water in a curious way, and far as I was from him, I could have sworn he was trembling.",
                    translationText = "Il tendit les bras vers l'eau sombre d'une façon curieuse, et bien que j'en fusse éloigné, j'aurais juré qu'il tremblait."
                ),
                BilingualParagraph(
                    targetText = "I distinguished nothing except a single green light, minute and far away, that might have been the end of a dock.",
                    translationText = "Je ne distinguai rien d'autre qu'une unique lumière verte, minuscule et lointaine, qui aurait pu être l'extrémité d'un ponton."
                )
            )
        ),
        Book(
            id = "en-05-time-machine",
            title = "The Voyage into Tomorrow",
            translatedTitle = "The Voyage into Tomorrow",
            author = "H.G. Wells",
            description = "An inventor pulls the brass lever of his machine to hurtle hundreds of thousands of years into future Earth.",
            targetLanguage = Language.ENGLISH,
            difficulty = DifficultyLevel.B2,
            coverEmoji = "⏳",
            coverGradientStart = 0xFF312E81,
            coverGradientEnd = 0xFF4338CA,
            coverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
            totalWords = 280,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "I drew a long breath, gripped the starting lever with both hands, and pushed it forward.",
                    translationText = "Je pris une profonde inspiration, saisis le levier de démarrage à deux mains et le poussai en avant."
                ),
                BilingualParagraph(
                    targetText = "Night followed day like the flapping of a black wing, and the sun hopped through the sky in a blinding arc.",
                    translationText = "La nuit succédait au jour comme le battement d'une aile noire, et le soleil bondissait dans le ciel en un arc aveuglant."
                )
            )
        ),
        Book(
            id = "en-06-robin-hood",
            title = "The Outlaws of Sherwood Forest",
            translatedTitle = "The Outlaws of Sherwood Forest",
            author = "Howard Pyle",
            description = "Robin Hood, Little John, and the merry archers standing up for the poor beneath the great greenwood oaks.",
            targetLanguage = Language.ENGLISH,
            difficulty = DifficultyLevel.A2,
            coverEmoji = "🏹",
            coverGradientStart = 0xFF047857,
            coverGradientEnd = 0xFF065F46,
            coverTheme = BookCoverTheme.NOBLE_EMERALD,
            totalWords = 250,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "In merry Sherwood Forest, under the spreading branches of the Major Oak, Robin Hood tested the tension of his yew bow.",
                    translationText = "Dans la joyeuse forêt de Sherwood, sous les branches déployées du Grand Chêne, Robin des Bois testait la tension de son arc en if."
                ),
                BilingualParagraph(
                    targetText = "No honest traveler who had little to spare ever suffered harm at the hands of the bold outlaws.",
                    translationText = "Aucun voyageur honnête qui avait peu à donner ne subit jamais de mal de la part des hardis hors-la-loi."
                )
            )
        ),
        Book(
            id = "en-07-london-fog",
            title = "A Walk Through Victorian London",
            translatedTitle = "A Walk Through Victorian London",
            author = "Charles Bennett",
            description = "Gas lamps, hansom cabs, and cobblestones alongside the Thames river in 1890.",
            targetLanguage = Language.ENGLISH,
            difficulty = DifficultyLevel.A1,
            coverEmoji = "🕰️",
            coverGradientStart = 0xFF1E293B,
            coverGradientEnd = 0xFF475569,
            coverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
            totalWords = 210,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "The yellow fog rolled gently through the narrow streets of London as Big Ben struck eight o'clock in the evening.",
                    translationText = "Le brouillard jaunâtre roulait doucement dans les ruelles étroites de Londres alors que Big Ben sonnait huit heures du soir."
                ),
                BilingualParagraph(
                    targetText = "Horses trotted with a rhythmic clatter over wet stones while warm tea awaited inside cozy parlors.",
                    translationText = "Les chevaux trottaient dans un claquement rythmé sur les pavés mouillés tandis que du thé chaud attendait dans les salons douillets."
                )
            )
        ),
        Book(
            id = "en-08-dracula-castle",
            title = "Arrival in the Carpathian Mountains",
            translatedTitle = "Arrival in the Carpathian Mountains",
            author = "Bram Stoker",
            description = "Jonathan Harker's carriage ascending through dense pine forests toward Castle Dracula.",
            targetLanguage = Language.ENGLISH,
            difficulty = DifficultyLevel.B2,
            coverEmoji = "🏰",
            coverGradientStart = 0xFF7F1D1D,
            coverGradientEnd = 0xFF991B1B,
            coverTheme = BookCoverTheme.CRIMSON_RUBY,
            totalWords = 270,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "The shadows of the evening began to creep on, and the deep gloom of the towering Carpathians seemed to close in around us.",
                    translationText = "Les ombres du soir commencèrent à s'étendre, et la profonde obscurité des Carpates imposantes semblait se refermer autour de nous."
                ),
                BilingualParagraph(
                    targetText = "Far above, against the moonlit sky, stood the jagged silhouette of a vast ruined castle.",
                    translationText = "Tout en haut, se découpant sur le ciel éclairé par la lune, se dressait la silhouette déchiquetée d'un vaste château en ruines."
                )
            )
        ),
        Book(
            id = "en-09-call-of-wild",
            title = "The Call of the Yukon Gold Trail",
            translatedTitle = "The Call of the Yukon Gold Trail",
            author = "Jack London",
            description = "Buck the loyal sled dog braving icy blizzards and howling wolves in the Canadian North.",
            targetLanguage = Language.ENGLISH,
            difficulty = DifficultyLevel.B1,
            coverEmoji = "🐺",
            coverGradientStart = 0xFF0F172A,
            coverGradientEnd = 0xFF0284C7,
            coverTheme = BookCoverTheme.CELESTIAL_INDIGO,
            totalWords = 260,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "The snow was powdery and deep, and forty degrees below zero bit into every breath of the husky team.",
                    translationText = "La neige était poudreuse et profonde, et quarante degrés sous zéro mordaient chaque souffle de l'attelage de huskies."
                ),
                BilingualParagraph(
                    targetText = "Deep in the primeval forest, an ancient instinct stirred in Buck that had slept for generations.",
                    translationText = "Au fond de la forêt primitive, un instinct ancien s'éveilla chez Buck, qui avait sommeillé pendant des générations."
                )
            )
        ),
        Book(
            id = "en-10-secret-garden",
            title = "The Key to the Hidden Garden",
            translatedTitle = "The Key to the Hidden Garden",
            author = "Frances Hodgson Burnett",
            description = "Mary discovers a rusty brass key buried in the earth that opens an ivy-covered locked door.",
            targetLanguage = Language.ENGLISH,
            difficulty = DifficultyLevel.A2,
            coverEmoji = "🗝️",
            coverGradientStart = 0xFF065F46,
            coverGradientEnd = 0xFF047857,
            coverTheme = BookCoverTheme.NOBLE_EMERALD,
            totalWords = 240,
            explicitPageCount = 2,
            paragraphs = listOf(
                BilingualParagraph(
                    targetText = "The robin hopped upon the newly turned soil, tilting his head curious as Mary saw something half-buried in the grass.",
                    translationText = "Le rouge-gorge sautilla sur la terre fraîchement remuée, penchant curieusement la tête tandis que Mary apercevait quelque chose à demi enfoui dans l'herbe."
                ),
                BilingualParagraph(
                    targetText = "It was an old rusty key, and she wondered if it might unlock the secret door hidden behind thick ivy.",
                    translationText = "C'était une vieille clé rouillée, et elle se demanda si elle pouvait déverrouiller la porte secrète cachée derrière le lierre épais."
                )
            )
        )
    )
}
