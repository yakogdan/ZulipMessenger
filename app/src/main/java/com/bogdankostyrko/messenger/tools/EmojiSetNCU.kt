package com.bogdankostyrko.messenger.tools

import com.bogdankostyrko.messenger.domain.models.ReactionModel

// Class Emoji with Name and Code as Unicode (Int)
data class EmojiNCU(
    val name: String,
    val code: Int,
) {
    fun getCodeString() = String(Character.toChars(code))
}

fun EmojiNCU.toReactionModel() = ReactionModel(
    emojiCode = getCodeString(),
    emojiName = this.name,
)

val emojiSetNCU = listOf(

// Smileys & Emotion
    EmojiNCU("grinning", 0x1f600),
    EmojiNCU("smiley", 0x1f603),
    EmojiNCU("big_smile", 0x1f604),
    EmojiNCU("grinning_face_with_smiling_eyes", 0x1f601),
    EmojiNCU("laughing", 0x1f606),
    EmojiNCU("sweat_smile", 0x1f605),
    EmojiNCU("rolling_on_the_floor_laughing", 0x1f923),
    EmojiNCU("joy", 0x1f602),
    EmojiNCU("smile", 0x1f642),
    EmojiNCU("upside_down", 0x1f643),
    EmojiNCU("wink", 0x1f609),
    EmojiNCU("blush", 0x1f60a),
    EmojiNCU("innocent", 0x1f607),
    EmojiNCU("heart_eyes", 0x1f60d),
    EmojiNCU("heart_kiss", 0x1f618),
    EmojiNCU("kiss", 0x1f617),
    EmojiNCU("smiling_face", 0x263a),
    EmojiNCU("kiss_with_blush", 0x1f61a),
    EmojiNCU("kiss_smiling_eyes", 0x1f619),
    EmojiNCU("yum", 0x1f60b),
    EmojiNCU("stuck_out_tongue", 0x1f61b),
    EmojiNCU("stuck_out_tongue_wink", 0x1f61c),
    EmojiNCU("stuck_out_tongue_closed_eyes", 0x1f61d),
    EmojiNCU("money_face", 0x1f911),
    EmojiNCU("hug", 0x1f917),
    EmojiNCU("thinking", 0x1f914),
    EmojiNCU("silence", 0x1f910),
    EmojiNCU("neutral", 0x1f610),
    EmojiNCU("expressionless", 0x1f611),
    EmojiNCU("speechless", 0x1f636),
    EmojiNCU("smirk", 0x1f60f),
    EmojiNCU("unamused", 0x1f612),
    EmojiNCU("rolling_eyes", 0x1f644),
    EmojiNCU("grimacing", 0x1f62c),
    EmojiNCU("lying", 0x1f925),
    EmojiNCU("relieved", 0x1f60c),
    EmojiNCU("pensive", 0x1f614),
    EmojiNCU("sleepy", 0x1f62a),
    EmojiNCU("drooling", 0x1f924),
    EmojiNCU("sleeping", 0x1f634),
    EmojiNCU("cant_talk", 0x1f637),
    EmojiNCU("sick", 0x1f912),
    EmojiNCU("hurt", 0x1f915),
    EmojiNCU("nauseated", 0x1f922),
    EmojiNCU("sneezing", 0x1f927),
    EmojiNCU("dizzy", 0x1f635),
    EmojiNCU("cowboy", 0x1f920),
    EmojiNCU("sunglasses", 0x1f60e),
    EmojiNCU("nerd", 0x1f913),
    EmojiNCU("oh_no", 0x1f615),
    EmojiNCU("worried", 0x1f61f),
    EmojiNCU("frown", 0x1f641),
    EmojiNCU("open_mouth", 0x1f62e),
    EmojiNCU("hushed", 0x1f62f),
    EmojiNCU("astonished", 0x1f632),
    EmojiNCU("flushed", 0x1f633),
    EmojiNCU("frowning", 0x1f626),
    EmojiNCU("anguished", 0x1f627),
    EmojiNCU("fear", 0x1f628),
    EmojiNCU("cold_sweat", 0x1f630),
    EmojiNCU("exhausted", 0x1f625),
    EmojiNCU("cry", 0x1f622),
    EmojiNCU("sob", 0x1f62d),
    EmojiNCU("scream", 0x1f631),
    EmojiNCU("confounded", 0x1f616),
    EmojiNCU("persevere", 0x1f623),
    EmojiNCU("disappointed", 0x1f61e),
    EmojiNCU("sweat", 0x1f613),
    EmojiNCU("weary", 0x1f629),
    EmojiNCU("anguish", 0x1f62b),
    EmojiNCU("triumph", 0x1f624),
    EmojiNCU("rage", 0x1f621),
    EmojiNCU("angry", 0x1f620),
    EmojiNCU("smiling_devil", 0x1f608),
    EmojiNCU("devil", 0x1f47f),
    EmojiNCU("skull", 0x1f480),
    EmojiNCU("poop", 0x1f4a9),
    EmojiNCU("clown", 0x1f921),
    EmojiNCU("ogre", 0x1f479),
    EmojiNCU("goblin", 0x1f47a),
    EmojiNCU("ghost", 0x1f47b),
    EmojiNCU("alien", 0x1f47d),
    EmojiNCU("space_invader", 0x1f47e),
    EmojiNCU("robot", 0x1f916),
    EmojiNCU("smiley_cat", 0x1f63a),
    EmojiNCU("smile_cat", 0x1f638),
    EmojiNCU("joy_cat", 0x1f639),
    EmojiNCU("heart_eyes_cat", 0x1f63b),
    EmojiNCU("smirk_cat", 0x1f63c),
    EmojiNCU("kissing_cat", 0x1f63d),
    EmojiNCU("scream_cat", 0x1f640),
    EmojiNCU("crying_cat", 0x1f63f),
    EmojiNCU("angry_cat", 0x1f63e),
    EmojiNCU("see_no_evil", 0x1f648),
    EmojiNCU("hear_no_evil", 0x1f649),
    EmojiNCU("speak_no_evil", 0x1f64a),
    EmojiNCU("lipstick_kiss", 0x1f48b),
    EmojiNCU("love_letter", 0x1f48c),
    EmojiNCU("cupid", 0x1f498),
    EmojiNCU("gift_heart", 0x1f49d),
    EmojiNCU("sparkling_heart", 0x1f496),
    EmojiNCU("heart_pulse", 0x1f497),
    EmojiNCU("heartbeat", 0x1f493),
    EmojiNCU("revolving_hearts", 0x1f49e),
    EmojiNCU("two_hearts", 0x1f495),
    EmojiNCU("heart_box", 0x1f49f),
    EmojiNCU("broken_heart", 0x1f494),
    EmojiNCU("heart", 0x2764),
    EmojiNCU("yellow_heart", 0x1f49b),
    EmojiNCU("green_heart", 0x1f49a),
    EmojiNCU("blue_heart", 0x1f499),
    EmojiNCU("purple_heart", 0x1f49c),
    EmojiNCU("black_heart", 0x1f5a4),
    EmojiNCU("100", 0x1f4af),
    EmojiNCU("anger", 0x1f4a2),
    EmojiNCU("boom", 0x1f4a5),
    EmojiNCU("seeing_stars", 0x1f4ab),
    EmojiNCU("sweat_drops", 0x1f4a6),
    EmojiNCU("dash", 0x1f4a8),
    EmojiNCU("hole", 0x1f573),
    EmojiNCU("bomb", 0x1f4a3),
    EmojiNCU("umm", 0x1f4ac),
    EmojiNCU("speech_bubble", 0x1f5e8),
    EmojiNCU("anger_bubble", 0x1f5ef),
    EmojiNCU("thought", 0x1f4ad),
    EmojiNCU("zzz", 0x1f4a4),

// People & Body
    EmojiNCU("wave", 0x1f44b),
    EmojiNCU("stop", 0x1f91a),
    EmojiNCU("high_five", 0x1f590),
    EmojiNCU("hand", 0x270b),
    EmojiNCU("spock", 0x1f596),
    EmojiNCU("ok", 0x1f44c),
    EmojiNCU("peace_sign", 0x270c),
    EmojiNCU("fingers_crossed", 0x1f91e),
    EmojiNCU("rock_on", 0x1f918),
    EmojiNCU("call_me", 0x1f919),
    EmojiNCU("point_left", 0x1f448),
    EmojiNCU("point_right", 0x1f449),
    EmojiNCU("point_up", 0x1f446),
    EmojiNCU("middle_finger", 0x1f595),
    EmojiNCU("point_down", 0x1f447),
    EmojiNCU("wait_one_second", 0x261d),
    EmojiNCU("+1", 0x1f44d),
    EmojiNCU("-1", 0x1f44e),
    EmojiNCU("fist", 0x270a),
    EmojiNCU("fist_bump", 0x1f44a),
    EmojiNCU("left_fist", 0x1f91b),
    EmojiNCU("right_fist", 0x1f91c),
    EmojiNCU("clap", 0x1f44f),
    EmojiNCU("raised_hands", 0x1f64c),
    EmojiNCU("open_hands", 0x1f450),
    EmojiNCU("handshake", 0x1f91d),
    EmojiNCU("pray", 0x1f64f),
    EmojiNCU("nail_polish", 0x1f485),
    EmojiNCU("selfie", 0x1f933),
    EmojiNCU("muscle", 0x1f4aa),
    EmojiNCU("ear", 0x1f442),
    EmojiNCU("nose", 0x1f443),
    EmojiNCU("eyes", 0x1f440),
    EmojiNCU("eye", 0x1f441),
    EmojiNCU("tongue", 0x1f445),
    EmojiNCU("lips", 0x1f444),
    EmojiNCU("baby", 0x1f476),
    EmojiNCU("boy", 0x1f466),
    EmojiNCU("girl", 0x1f467),
    EmojiNCU("man", 0x1f468),
    EmojiNCU("woman", 0x1f469),
    EmojiNCU("older_man", 0x1f474),
    EmojiNCU("older_woman", 0x1f475),
    EmojiNCU("person_frowning", 0x1f64d),
    EmojiNCU("person_pouting", 0x1f64e),
    EmojiNCU("no_signal", 0x1f645),
    EmojiNCU("ok_signal", 0x1f646),
    EmojiNCU("information_desk_person", 0x1f481),
    EmojiNCU("raising_hand", 0x1f64b),
    EmojiNCU("bow", 0x1f647),
    EmojiNCU("face_palm", 0x1f926),
    EmojiNCU("shrug", 0x1f937),
    EmojiNCU("police", 0x1f46e),
    EmojiNCU("detective", 0x1f575),
    EmojiNCU("guard", 0x1f482),
    EmojiNCU("construction_worker", 0x1f477),
    EmojiNCU("prince", 0x1f934),
    EmojiNCU("princess", 0x1f478),
    EmojiNCU("turban", 0x1f473),
    EmojiNCU("gua_pi_mao", 0x1f472),
    EmojiNCU("bride", 0x1f470),
    EmojiNCU("pregnant", 0x1f930),
    EmojiNCU("angel", 0x1f47c),
    EmojiNCU("santa", 0x1f385),
    EmojiNCU("mother_christmas", 0x1f936),
    EmojiNCU("massage", 0x1f486),
    EmojiNCU("haircut", 0x1f487),
    EmojiNCU("walking", 0x1f6b6),
    EmojiNCU("running", 0x1f3c3),
    EmojiNCU("dancer", 0x1f483),
    EmojiNCU("dancing", 0x1f57a),
    EmojiNCU("levitating", 0x1f574),
    EmojiNCU("dancers", 0x1f46f),
    EmojiNCU("fencing", 0x1f93a),
    EmojiNCU("horse_racing", 0x1f3c7),
    EmojiNCU("snowboarder", 0x1f3c2),
    EmojiNCU("golf", 0x1f3cc),
    EmojiNCU("surf", 0x1f3c4),
    EmojiNCU("rowboat", 0x1f6a3),
    EmojiNCU("swim", 0x1f3ca),
    EmojiNCU("lift", 0x1f3cb),
    EmojiNCU("cyclist", 0x1f6b4),
    EmojiNCU("mountain_biker", 0x1f6b5),
    EmojiNCU("cartwheel", 0x1f938),
    EmojiNCU("wrestling", 0x1f93c),
    EmojiNCU("water_polo", 0x1f93d),
    EmojiNCU("handball", 0x1f93e),
    EmojiNCU("juggling", 0x1f939),
    EmojiNCU("bath", 0x1f6c0),
    EmojiNCU("in_bed", 0x1f6cc),
    EmojiNCU("two_women_holding_hands", 0x1f46d),
    EmojiNCU("man_and_woman_holding_hands", 0x1f46b),
    EmojiNCU("two_men_holding_hands", 0x1f46c),
    EmojiNCU("family", 0x1f46a),
    EmojiNCU("speaking_head", 0x1f5e3),
    EmojiNCU("silhouette", 0x1f464),
    EmojiNCU("silhouettes", 0x1f465),
    EmojiNCU("footprints", 0x1f463),
    EmojiNCU("tuxedo", 0x1f935),

// Animals & Nature
    EmojiNCU("monkey_face", 0x1f435),
    EmojiNCU("monkey", 0x1f412),
    EmojiNCU("gorilla", 0x1f98d),
    EmojiNCU("puppy", 0x1f436),
    EmojiNCU("dog", 0x1f415),
    EmojiNCU("poodle", 0x1f429),
    EmojiNCU("wolf", 0x1f43a),
    EmojiNCU("fox", 0x1f98a),
    EmojiNCU("kitten", 0x1f431),
    EmojiNCU("cat", 0x1f408),
    EmojiNCU("lion", 0x1f981),
    EmojiNCU("tiger_cub", 0x1f42f),
    EmojiNCU("tiger", 0x1f405),
    EmojiNCU("leopard", 0x1f406),
    EmojiNCU("pony", 0x1f434),
    EmojiNCU("horse", 0x1f40e),
    EmojiNCU("unicorn", 0x1f984),
    EmojiNCU("deer", 0x1f98c),
    EmojiNCU("calf", 0x1f42e),
    EmojiNCU("ox", 0x1f402),
    EmojiNCU("water_buffalo", 0x1f403),
    EmojiNCU("cow", 0x1f404),
    EmojiNCU("piglet", 0x1f437),
    EmojiNCU("pig", 0x1f416),
    EmojiNCU("boar", 0x1f417),
    EmojiNCU("pig_nose", 0x1f43d),
    EmojiNCU("ram", 0x1f40f),
    EmojiNCU("sheep", 0x1f411),
    EmojiNCU("goat", 0x1f410),
    EmojiNCU("arabian_camel", 0x1f42a),
    EmojiNCU("camel", 0x1f42b),
    EmojiNCU("elephant", 0x1f418),
    EmojiNCU("rhinoceros", 0x1f98f),
    EmojiNCU("dormouse", 0x1f42d),
    EmojiNCU("mouse", 0x1f401),
    EmojiNCU("rat", 0x1f400),
    EmojiNCU("hamster", 0x1f439),
    EmojiNCU("bunny", 0x1f430),
    EmojiNCU("rabbit", 0x1f407),
    EmojiNCU("chipmunk", 0x1f43f),
    EmojiNCU("bat", 0x1f987),
    EmojiNCU("bear", 0x1f43b),
    EmojiNCU("koala", 0x1f428),
    EmojiNCU("panda", 0x1f43c),
    EmojiNCU("paw_prints", 0x1f43e),
    EmojiNCU("turkey", 0x1f983),
    EmojiNCU("chicken", 0x1f414),
    EmojiNCU("rooster", 0x1f413),
    EmojiNCU("hatching", 0x1f423),
    EmojiNCU("chick", 0x1f424),
    EmojiNCU("new_baby", 0x1f425),
    EmojiNCU("bird", 0x1f426),
    EmojiNCU("penguin", 0x1f427),
    EmojiNCU("dove", 0x1f54a),
    EmojiNCU("eagle", 0x1f985),
    EmojiNCU("duck", 0x1f986),
    EmojiNCU("owl", 0x1f989),
    EmojiNCU("frog", 0x1f438),
    EmojiNCU("crocodile", 0x1f40a),
    EmojiNCU("turtle", 0x1f422),
    EmojiNCU("lizard", 0x1f98e),
    EmojiNCU("snake", 0x1f40d),
    EmojiNCU("dragon_face", 0x1f432),
    EmojiNCU("dragon", 0x1f409),
    EmojiNCU("whale", 0x1f433),
    EmojiNCU("humpback_whale", 0x1f40b),
    EmojiNCU("dolphin", 0x1f42c),
    EmojiNCU("fish", 0x1f41f),
    EmojiNCU("tropical_fish", 0x1f420),
    EmojiNCU("blowfish", 0x1f421),
    EmojiNCU("shark", 0x1f988),
    EmojiNCU("octopus", 0x1f419),
    EmojiNCU("shell", 0x1f41a),
    EmojiNCU("snail", 0x1f40c),
    EmojiNCU("butterfly", 0x1f98b),
    EmojiNCU("bug", 0x1f41b),
    EmojiNCU("ant", 0x1f41c),
    EmojiNCU("bee", 0x1f41d),
    EmojiNCU("spider", 0x1f577),
    EmojiNCU("web", 0x1f578),
    EmojiNCU("scorpion", 0x1f982),
    EmojiNCU("bouquet", 0x1f490),
    EmojiNCU("cherry_blossom", 0x1f338),
    EmojiNCU("white_flower", 0x1f4ae),
    EmojiNCU("rosette", 0x1f3f5),
    EmojiNCU("rose", 0x1f339),
    EmojiNCU("wilted_flower", 0x1f940),
    EmojiNCU("hibiscus", 0x1f33a),
    EmojiNCU("sunflower", 0x1f33b),
    EmojiNCU("blossom", 0x1f33c),
    EmojiNCU("tulip", 0x1f337),
    EmojiNCU("seedling", 0x1f331),
    EmojiNCU("evergreen_tree", 0x1f332),
    EmojiNCU("tree", 0x1f333),
    EmojiNCU("palm_tree", 0x1f334),
    EmojiNCU("cactus", 0x1f335),
    EmojiNCU("harvest", 0x1f33e),
    EmojiNCU("herb", 0x1f33f),
    EmojiNCU("lucky", 0x1f340),
    EmojiNCU("maple_leaf", 0x1f341),
    EmojiNCU("fallen_leaf", 0x1f342),
    EmojiNCU("leaves", 0x1f343),
    EmojiNCU("beetle", 0x1f41e),

// Food & Drink
    EmojiNCU("grapes", 0x1f347),
    EmojiNCU("melon", 0x1f348),
    EmojiNCU("watermelon", 0x1f349),
    EmojiNCU("orange", 0x1f34a),
    EmojiNCU("lemon", 0x1f34b),
    EmojiNCU("banana", 0x1f34c),
    EmojiNCU("pineapple", 0x1f34d),
    EmojiNCU("apple", 0x1f34e),
    EmojiNCU("green_apple", 0x1f34f),
    EmojiNCU("pear", 0x1f350),
    EmojiNCU("peach", 0x1f351),
    EmojiNCU("cherries", 0x1f352),
    EmojiNCU("strawberry", 0x1f353),
    EmojiNCU("kiwi", 0x1f95d),
    EmojiNCU("tomato", 0x1f345),
    EmojiNCU("avocado", 0x1f951),
    EmojiNCU("eggplant", 0x1f346),
    EmojiNCU("potato", 0x1f954),
    EmojiNCU("carrot", 0x1f955),
    EmojiNCU("corn", 0x1f33d),
    EmojiNCU("hot_pepper", 0x1f336),
    EmojiNCU("cucumber", 0x1f952),
    EmojiNCU("mushroom", 0x1f344),
    EmojiNCU("peanuts", 0x1f95c),
    EmojiNCU("chestnut", 0x1f330),
    EmojiNCU("bread", 0x1f35e),
    EmojiNCU("croissant", 0x1f950),
    EmojiNCU("baguette", 0x1f956),
    EmojiNCU("pancakes", 0x1f95e),
    EmojiNCU("cheese", 0x1f9c0),
    EmojiNCU("meat", 0x1f356),
    EmojiNCU("drumstick", 0x1f357),
    EmojiNCU("bacon", 0x1f953),
    EmojiNCU("hamburger", 0x1f354),
    EmojiNCU("fries", 0x1f35f),
    EmojiNCU("pizza", 0x1f355),
    EmojiNCU("hotdog", 0x1f32d),
    EmojiNCU("taco", 0x1f32e),
    EmojiNCU("burrito", 0x1f32f),
    EmojiNCU("doner_kebab", 0x1f959),
    EmojiNCU("egg", 0x1f95a),
    EmojiNCU("cooking", 0x1f373),
    EmojiNCU("paella", 0x1f958),
    EmojiNCU("food", 0x1f372),
    EmojiNCU("salad", 0x1f957),
    EmojiNCU("popcorn", 0x1f37f),
    EmojiNCU("bento", 0x1f371),
    EmojiNCU("senbei", 0x1f358),
    EmojiNCU("onigiri", 0x1f359),
    EmojiNCU("rice", 0x1f35a),
    EmojiNCU("curry", 0x1f35b),
    EmojiNCU("ramen", 0x1f35c),
    EmojiNCU("spaghetti", 0x1f35d),
    EmojiNCU("yam", 0x1f360),
    EmojiNCU("oden", 0x1f362),
    EmojiNCU("sushi", 0x1f363),
    EmojiNCU("tempura", 0x1f364),
    EmojiNCU("naruto", 0x1f365),
    EmojiNCU("dango", 0x1f361),
    EmojiNCU("crab", 0x1f980),
    EmojiNCU("shrimp", 0x1f990),
    EmojiNCU("squid", 0x1f991),
    EmojiNCU("soft_serve", 0x1f366),
    EmojiNCU("shaved_ice", 0x1f367),
    EmojiNCU("ice_cream", 0x1f368),
    EmojiNCU("donut", 0x1f369),
    EmojiNCU("cookie", 0x1f36a),
    EmojiNCU("birthday", 0x1f382),
    EmojiNCU("cake", 0x1f370),
    EmojiNCU("chocolate", 0x1f36b),
    EmojiNCU("candy", 0x1f36c),
    EmojiNCU("lollipop", 0x1f36d),
    EmojiNCU("custard", 0x1f36e),
    EmojiNCU("honey", 0x1f36f),
    EmojiNCU("baby_bottle", 0x1f37c),
    EmojiNCU("milk", 0x1f95b),
    EmojiNCU("coffee", 0x2615),
    EmojiNCU("tea", 0x1f375),
    EmojiNCU("sake", 0x1f376),
    EmojiNCU("champagne", 0x1f37e),
    EmojiNCU("wine", 0x1f377),
    EmojiNCU("cocktail", 0x1f378),
    EmojiNCU("tropical_drink", 0x1f379),
    EmojiNCU("beer", 0x1f37a),
    EmojiNCU("beers", 0x1f37b),
    EmojiNCU("clink", 0x1f942),
    EmojiNCU("small_glass", 0x1f943),
    EmojiNCU("hungry", 0x1f37d),
    EmojiNCU("fork_and_knife", 0x1f374),
    EmojiNCU("spoon", 0x1f944),
    EmojiNCU("knife", 0x1f52a),
    EmojiNCU("vase", 0x1f3fa),

// Activities
    EmojiNCU("jack-o-lantern", 0x1f383),
    EmojiNCU("holiday_tree", 0x1f384),
    EmojiNCU("fireworks", 0x1f386),
    EmojiNCU("sparkler", 0x1f387),
    EmojiNCU("sparkles", 0x2728),
    EmojiNCU("balloon", 0x1f388),
    EmojiNCU("tada", 0x1f389),
    EmojiNCU("confetti", 0x1f38a),
    EmojiNCU("wish_tree", 0x1f38b),
    EmojiNCU("bamboo", 0x1f38d),
    EmojiNCU("dolls", 0x1f38e),
    EmojiNCU("carp_streamer", 0x1f38f),
    EmojiNCU("wind_chime", 0x1f390),
    EmojiNCU("moon_ceremony", 0x1f391),
    EmojiNCU("ribbon", 0x1f380),
    EmojiNCU("gift", 0x1f381),
    EmojiNCU("reminder_ribbon", 0x1f397),
    EmojiNCU("ticket", 0x1f39f),
    EmojiNCU("pass", 0x1f3ab),
    EmojiNCU("military_medal", 0x1f396),
    EmojiNCU("trophy", 0x1f3c6),
    EmojiNCU("medal", 0x1f3c5),
    EmojiNCU("first_place", 0x1f947),
    EmojiNCU("second_place", 0x1f948),
    EmojiNCU("third_place", 0x1f949),
    EmojiNCU("football", 0x26bd),
    EmojiNCU("baseball", 0x26be),
    EmojiNCU("basketball", 0x1f3c0),
    EmojiNCU("volleyball", 0x1f3d0),
    EmojiNCU("american_football", 0x1f3c8),
    EmojiNCU("rugby", 0x1f3c9),
    EmojiNCU("tennis", 0x1f3be),
    EmojiNCU("strike", 0x1f3b3),
    EmojiNCU("cricket", 0x1f3cf),
    EmojiNCU("field_hockey", 0x1f3d1),
    EmojiNCU("ice_hockey", 0x1f3d2),
    EmojiNCU("ping_pong", 0x1f3d3),
    EmojiNCU("badminton", 0x1f3f8),
    EmojiNCU("boxing_glove", 0x1f94a),
    EmojiNCU("black_belt", 0x1f94b),
    EmojiNCU("gooooooooal", 0x1f945),
    EmojiNCU("hole_in_one", 0x26f3),
    EmojiNCU("fishing", 0x1f3a3),
    EmojiNCU("running_shirt", 0x1f3bd),
    EmojiNCU("ski", 0x1f3bf),
    EmojiNCU("direct_hit", 0x1f3af),
    EmojiNCU("billiards", 0x1f3b1),
    EmojiNCU("crystal_ball", 0x1f52e),
    EmojiNCU("video_game", 0x1f3ae),
    EmojiNCU("joystick", 0x1f579),
    EmojiNCU("slot_machine", 0x1f3b0),
    EmojiNCU("dice", 0x1f3b2),
    EmojiNCU("spades", 0x2660),
    EmojiNCU("hearts", 0x2665),
    EmojiNCU("diamonds", 0x2666),
    EmojiNCU("clubs", 0x2663),
    EmojiNCU("joker", 0x1f0cf),
    EmojiNCU("mahjong", 0x1f004),
    EmojiNCU("playing_cards", 0x1f3b4),
    EmojiNCU("performing_arts", 0x1f3ad),
    EmojiNCU("picture", 0x1f5bc),
    EmojiNCU("art", 0x1f3a8),

// Travel & Places
    EmojiNCU("earth_africa", 0x1f30d),
    EmojiNCU("earth_americas", 0x1f30e),
    EmojiNCU("earth_asia", 0x1f30f),
    EmojiNCU("www", 0x1f310),
    EmojiNCU("map", 0x1f5fa),
    EmojiNCU("japan", 0x1f5fe),
    EmojiNCU("snowy_mountain", 0x1f3d4),
    EmojiNCU("volcano", 0x1f30b),
    EmojiNCU("mount_fuji", 0x1f5fb),
    EmojiNCU("campsite", 0x1f3d5),
    EmojiNCU("beach", 0x1f3d6),
    EmojiNCU("desert", 0x1f3dc),
    EmojiNCU("island", 0x1f3dd),
    EmojiNCU("national_park", 0x1f3de),
    EmojiNCU("stadium", 0x1f3df),
    EmojiNCU("classical_building", 0x1f3db),
    EmojiNCU("construction", 0x1f3d7),
    EmojiNCU("houses", 0x1f3d8),
    EmojiNCU("derelict_house", 0x1f3da),
    EmojiNCU("house", 0x1f3e0),
    EmojiNCU("suburb", 0x1f3e1),
    EmojiNCU("office", 0x1f3e2),
    EmojiNCU("japan_post", 0x1f3e3),
    EmojiNCU("post_office", 0x1f3e4),
    EmojiNCU("hospital", 0x1f3e5),
    EmojiNCU("bank", 0x1f3e6),
    EmojiNCU("hotel", 0x1f3e8),
    EmojiNCU("love_hotel", 0x1f3e9),
    EmojiNCU("convenience_store", 0x1f3ea),
    EmojiNCU("school", 0x1f3eb),
    EmojiNCU("department_store", 0x1f3ec),
    EmojiNCU("factory", 0x1f3ed),
    EmojiNCU("shiro", 0x1f3ef),
    EmojiNCU("castle", 0x1f3f0),
    EmojiNCU("wedding", 0x1f492),
    EmojiNCU("tower", 0x1f5fc),
    EmojiNCU("statue", 0x1f5fd),
    EmojiNCU("church", 0x26ea),
    EmojiNCU("mosque", 0x1f54c),
    EmojiNCU("synagogue", 0x1f54d),
    EmojiNCU("kaaba", 0x1f54b),
    EmojiNCU("fountain", 0x26f2),
    EmojiNCU("tent", 0x26fa),
    EmojiNCU("foggy", 0x1f301),
    EmojiNCU("night", 0x1f303),
    EmojiNCU("city", 0x1f3d9),
    EmojiNCU("mountain_sunrise", 0x1f304),
    EmojiNCU("sunrise", 0x1f305),
    EmojiNCU("sunset", 0x1f306),
    EmojiNCU("city_sunrise", 0x1f307),
    EmojiNCU("bridge", 0x1f309),
    EmojiNCU("carousel", 0x1f3a0),
    EmojiNCU("ferris_wheel", 0x1f3a1),
    EmojiNCU("roller_coaster", 0x1f3a2),
    EmojiNCU("barber", 0x1f488),
    EmojiNCU("circus", 0x1f3aa),
    EmojiNCU("train", 0x1f682),
    EmojiNCU("railway_car", 0x1f683),
    EmojiNCU("high_speed_train", 0x1f684),
    EmojiNCU("bullet_train", 0x1f685),
    EmojiNCU("oncoming_train", 0x1f686),
    EmojiNCU("subway", 0x1f687),
    EmojiNCU("light_rail", 0x1f688),
    EmojiNCU("station", 0x1f689),
    EmojiNCU("oncoming_tram", 0x1f68a),
    EmojiNCU("monorail", 0x1f69d),
    EmojiNCU("mountain_railway", 0x1f69e),
    EmojiNCU("tram", 0x1f68b),
    EmojiNCU("bus", 0x1f68c),
    EmojiNCU("oncoming_bus", 0x1f68d),
    EmojiNCU("trolley", 0x1f68e),
    EmojiNCU("minibus", 0x1f690),
    EmojiNCU("ambulance", 0x1f691),
    EmojiNCU("fire_truck", 0x1f692),
    EmojiNCU("police_car", 0x1f693),
    EmojiNCU("oncoming_police_car", 0x1f694),
    EmojiNCU("taxi", 0x1f695),
    EmojiNCU("oncoming_taxi", 0x1f696),
    EmojiNCU("car", 0x1f697),
    EmojiNCU("oncoming_car", 0x1f698),
    EmojiNCU("recreational_vehicle", 0x1f699),
    EmojiNCU("moving_truck", 0x1f69a),
    EmojiNCU("truck", 0x1f69b),
    EmojiNCU("tractor", 0x1f69c),
    EmojiNCU("racecar", 0x1f3ce),
    EmojiNCU("motorcycle", 0x1f3cd),
    EmojiNCU("scooter", 0x1f6f5),
    EmojiNCU("bike", 0x1f6b2),
    EmojiNCU("kick_scooter", 0x1f6f4),
    EmojiNCU("bus_stop", 0x1f68f),
    EmojiNCU("road", 0x1f6e3),
    EmojiNCU("railway_track", 0x1f6e4),
    EmojiNCU("oil_drum", 0x1f6e2),
    EmojiNCU("fuel_pump", 0x26fd),
    EmojiNCU("siren", 0x1f6a8),
    EmojiNCU("horizontal_traffic_light", 0x1f6a5),
    EmojiNCU("traffic_light", 0x1f6a6),
    EmojiNCU("stop_sign", 0x1f6d1),
    EmojiNCU("work_in_progress", 0x1f6a7),
    EmojiNCU("anchor", 0x2693),
    EmojiNCU("boat", 0x26f5),
    EmojiNCU("canoe", 0x1f6f6),
    EmojiNCU("speedboat", 0x1f6a4),
    EmojiNCU("passenger_ship", 0x1f6f3),
    EmojiNCU("motor_boat", 0x1f6e5),
    EmojiNCU("ship", 0x1f6a2),
    EmojiNCU("small_airplane", 0x1f6e9),
    EmojiNCU("take_off", 0x1f6eb),
    EmojiNCU("landing", 0x1f6ec),
    EmojiNCU("seat", 0x1f4ba),
    EmojiNCU("helicopter", 0x1f681),
    EmojiNCU("suspension_railway", 0x1f69f),
    EmojiNCU("gondola", 0x1f6a0),
    EmojiNCU("aerial_tramway", 0x1f6a1),
    EmojiNCU("satellite", 0x1f6f0),
    EmojiNCU("rocket", 0x1f680),
    EmojiNCU("bellhop_bell", 0x1f6ce),
    EmojiNCU("times_up", 0x231b),
    EmojiNCU("time_ticking", 0x23f3),
    EmojiNCU("watch", 0x231a),
    EmojiNCU("alarm_clock", 0x23f0),
    EmojiNCU("mantelpiece_clock", 0x1f570),
    EmojiNCU("time", 0x1f557),
    EmojiNCU("new_moon", 0x1f311),
    EmojiNCU("waxing_moon", 0x1f314),
    EmojiNCU("full_moon", 0x1f315),
    EmojiNCU("moon", 0x1f319),
    EmojiNCU("new_moon_face", 0x1f31a),
    EmojiNCU("goodnight", 0x1f31b),
    EmojiNCU("temperature", 0x1f321),
    EmojiNCU("sunny", 0x2600),
    EmojiNCU("moon_face", 0x1f31d),
    EmojiNCU("sun_face", 0x1f31e),
    EmojiNCU("star", 0x2b50),
    EmojiNCU("glowing_star", 0x1f31f),
    EmojiNCU("shooting_star", 0x1f320),
    EmojiNCU("milky_way", 0x1f30c),
    EmojiNCU("cloud", 0x2601),
    EmojiNCU("partly_sunny", 0x26c5),
    EmojiNCU("mostly_sunny", 0x1f324),
    EmojiNCU("cloudy", 0x1f325),
    EmojiNCU("sunshowers", 0x1f326),
    EmojiNCU("rainy", 0x1f327),
    EmojiNCU("snowy", 0x1f328),
    EmojiNCU("lightning", 0x1f329),
    EmojiNCU("tornado", 0x1f32a),
    EmojiNCU("fog", 0x1f32b),
    EmojiNCU("windy", 0x1f32c),
    EmojiNCU("cyclone", 0x1f300),
    EmojiNCU("rainbow", 0x1f308),
    EmojiNCU("closed_umbrella", 0x1f302),
    EmojiNCU("umbrella_with_rain", 0x2614),
    EmojiNCU("high_voltage", 0x26a1),
    EmojiNCU("snowflake", 0x2744),
    EmojiNCU("frosty", 0x26c4),
    EmojiNCU("fire", 0x1f525),
    EmojiNCU("drop", 0x1f4a7),
    EmojiNCU("ocean", 0x1f30a),

// Objects
    EmojiNCU("glasses", 0x1f453),
    EmojiNCU("dark_sunglasses", 0x1f576),
    EmojiNCU("tie", 0x1f454),
    EmojiNCU("shirt", 0x1f455),
    EmojiNCU("jeans", 0x1f456),
    EmojiNCU("dress", 0x1f457),
    EmojiNCU("kimono", 0x1f458),
    EmojiNCU("bikini", 0x1f459),
    EmojiNCU("clothing", 0x1f45a),
    EmojiNCU("purse", 0x1f45b),
    EmojiNCU("handbag", 0x1f45c),
    EmojiNCU("pouch", 0x1f45d),
    EmojiNCU("shopping_bags", 0x1f6cd),
    EmojiNCU("backpack", 0x1f392),
    EmojiNCU("shoe", 0x1f45e),
    EmojiNCU("athletic_shoe", 0x1f45f),
    EmojiNCU("high_heels", 0x1f460),
    EmojiNCU("sandal", 0x1f461),
    EmojiNCU("boot", 0x1f462),
    EmojiNCU("crown", 0x1f451),
    EmojiNCU("hat", 0x1f452),
    EmojiNCU("top_hat", 0x1f3a9),
    EmojiNCU("graduate", 0x1f393),
    EmojiNCU("prayer_beads", 0x1f4ff),
    EmojiNCU("lipstick", 0x1f484),
    EmojiNCU("ring", 0x1f48d),
    EmojiNCU("gem", 0x1f48e),
    EmojiNCU("mute", 0x1f507),
    EmojiNCU("speaker", 0x1f508),
    EmojiNCU("softer", 0x1f509),
    EmojiNCU("louder", 0x1f50a),
    EmojiNCU("loudspeaker", 0x1f4e2),
    EmojiNCU("megaphone", 0x1f4e3),
    EmojiNCU("horn", 0x1f4ef),
    EmojiNCU("notifications", 0x1f514),
    EmojiNCU("mute_notifications", 0x1f515),
    EmojiNCU("musical_score", 0x1f3bc),
    EmojiNCU("music", 0x1f3b5),
    EmojiNCU("musical_notes", 0x1f3b6),
    EmojiNCU("studio_microphone", 0x1f399),
    EmojiNCU("volume", 0x1f39a),
    EmojiNCU("control_knobs", 0x1f39b),
    EmojiNCU("microphone", 0x1f3a4),
    EmojiNCU("headphones", 0x1f3a7),
    EmojiNCU("radio", 0x1f4fb),
    EmojiNCU("saxophone", 0x1f3b7),
    EmojiNCU("guitar", 0x1f3b8),
    EmojiNCU("piano", 0x1f3b9),
    EmojiNCU("trumpet", 0x1f3ba),
    EmojiNCU("violin", 0x1f3bb),
    EmojiNCU("drum", 0x1f941),
    EmojiNCU("mobile_phone", 0x1f4f1),
    EmojiNCU("calling", 0x1f4f2),
    EmojiNCU("phone", 0x260e),
    EmojiNCU("landline", 0x1f4de),
    EmojiNCU("pager", 0x1f4df),
    EmojiNCU("fax", 0x1f4e0),
    EmojiNCU("battery", 0x1f50b),
    EmojiNCU("electric_plug", 0x1f50c),
    EmojiNCU("computer", 0x1f4bb),
    EmojiNCU("desktop_computer", 0x1f5a5),
    EmojiNCU("printer", 0x1f5a8),
    EmojiNCU("computer_mouse", 0x1f5b1),
    EmojiNCU("trackball", 0x1f5b2),
    EmojiNCU("gold_record", 0x1f4bd),
    EmojiNCU("floppy_disk", 0x1f4be),
    EmojiNCU("cd", 0x1f4bf),
    EmojiNCU("dvd", 0x1f4c0),
    EmojiNCU("movie_camera", 0x1f3a5),
    EmojiNCU("film", 0x1f39e),
    EmojiNCU("projector", 0x1f4fd),
    EmojiNCU("action", 0x1f3ac),
    EmojiNCU("tv", 0x1f4fa),
    EmojiNCU("camera", 0x1f4f7),
    EmojiNCU("taking_a_picture", 0x1f4f8),
    EmojiNCU("video_camera", 0x1f4f9),
    EmojiNCU("vhs", 0x1f4fc),
    EmojiNCU("search", 0x1f50d),
    EmojiNCU("candle", 0x1f56f),
    EmojiNCU("light_bulb", 0x1f4a1),
    EmojiNCU("flashlight", 0x1f526),
    EmojiNCU("lantern", 0x1f3ee),
    EmojiNCU("decorative_notebook", 0x1f4d4),
    EmojiNCU("red_book", 0x1f4d5),
    EmojiNCU("book", 0x1f4d6),
    EmojiNCU("green_book", 0x1f4d7),
    EmojiNCU("blue_book", 0x1f4d8),
    EmojiNCU("orange_book", 0x1f4d9),
    EmojiNCU("books", 0x1f4da),
    EmojiNCU("notebook", 0x1f4d3),
    EmojiNCU("ledger", 0x1f4d2),
    EmojiNCU("receipt", 0x1f4c3),
    EmojiNCU("scroll", 0x1f4dc),
    EmojiNCU("document", 0x1f4c4),
    EmojiNCU("headlines", 0x1f4f0),
    EmojiNCU("newspaper", 0x1f5de),
    EmojiNCU("place_holder", 0x1f4d1),
    EmojiNCU("bookmark", 0x1f516),
    EmojiNCU("label", 0x1f3f7),
    EmojiNCU("money", 0x1f4b0),
    EmojiNCU("yen_banknotes", 0x1f4b4),
    EmojiNCU("dollar_bills", 0x1f4b5),
    EmojiNCU("euro_banknotes", 0x1f4b6),
    EmojiNCU("pound_notes", 0x1f4b7),
    EmojiNCU("losing_money", 0x1f4b8),
    EmojiNCU("credit_card", 0x1f4b3),
    EmojiNCU("stock_market", 0x1f4b9),
    EmojiNCU("e-mail", 0x1f4e7),
    EmojiNCU("mail_received", 0x1f4e8),
    EmojiNCU("mail_sent", 0x1f4e9),
    EmojiNCU("outbox", 0x1f4e4),
    EmojiNCU("inbox", 0x1f4e5),
    EmojiNCU("package", 0x1f4e6),
    EmojiNCU("mailbox", 0x1f4eb),
    EmojiNCU("closed_mailbox", 0x1f4ea),
    EmojiNCU("unread_mail", 0x1f4ec),
    EmojiNCU("inbox_zero", 0x1f4ed),
    EmojiNCU("mail_dropoff", 0x1f4ee),
    EmojiNCU("ballot_box", 0x1f5f3),
    EmojiNCU("fountain_pen", 0x1f58b),
    EmojiNCU("pen", 0x1f58a),
    EmojiNCU("paintbrush", 0x1f58c),
    EmojiNCU("crayon", 0x1f58d),
    EmojiNCU("memo", 0x1f4dd),
    EmojiNCU("briefcase", 0x1f4bc),
    EmojiNCU("organize", 0x1f4c1),
    EmojiNCU("folder", 0x1f4c2),
    EmojiNCU("sort", 0x1f5c2),
    EmojiNCU("calendar", 0x1f4c5),
    EmojiNCU("date", 0x1f4c6),
    EmojiNCU("spiral_notepad", 0x1f5d2),
    EmojiNCU("rolodex", 0x1f4c7),
    EmojiNCU("chart", 0x1f4c8),
    EmojiNCU("downwards_trend", 0x1f4c9),
    EmojiNCU("bar_chart", 0x1f4ca),
    EmojiNCU("clipboard", 0x1f4cb),
    EmojiNCU("push_pin", 0x1f4cc),
    EmojiNCU("pin", 0x1f4cd),
    EmojiNCU("paperclip", 0x1f4ce),
    EmojiNCU("office_supplies", 0x1f587),
    EmojiNCU("ruler", 0x1f4cf),
    EmojiNCU("carpenter_square", 0x1f4d0),
    EmojiNCU("archive", 0x1f5c3),
    EmojiNCU("file_cabinet", 0x1f5c4),
    EmojiNCU("wastebasket", 0x1f5d1),
    EmojiNCU("locked", 0x1f512),
    EmojiNCU("unlocked", 0x1f513),
    EmojiNCU("privacy", 0x1f50f),
    EmojiNCU("secure", 0x1f510),
    EmojiNCU("key", 0x1f511),
    EmojiNCU("secret", 0x1f5dd),
    EmojiNCU("hammer", 0x1f528),
    EmojiNCU("working_on_it", 0x1f6e0),
    EmojiNCU("dagger", 0x1f5e1),
    EmojiNCU("gun", 0x1f52b),
    EmojiNCU("bow_and_arrow", 0x1f3f9),
    EmojiNCU("shield", 0x1f6e1),
    EmojiNCU("fixing", 0x1f527),
    EmojiNCU("nut_and_bolt", 0x1f529),
    EmojiNCU("compression", 0x1f5dc),
    EmojiNCU("link", 0x1f517),
    EmojiNCU("science", 0x1f52c),
    EmojiNCU("telescope", 0x1f52d),
    EmojiNCU("satellite_antenna", 0x1f4e1),
    EmojiNCU("injection", 0x1f489),
    EmojiNCU("medicine", 0x1f48a),
    EmojiNCU("door", 0x1f6aa),
    EmojiNCU("bed", 0x1f6cf),
    EmojiNCU("living_room", 0x1f6cb),
    EmojiNCU("toilet", 0x1f6bd),
    EmojiNCU("shower", 0x1f6bf),
    EmojiNCU("bathtub", 0x1f6c1),
    EmojiNCU("shopping_cart", 0x1f6d2),
    EmojiNCU("smoking", 0x1f6ac),
    EmojiNCU("rock_carving", 0x1f5ff),

// Symbols
    EmojiNCU("atm", 0x1f3e7),
    EmojiNCU("put_litter_in_its_place", 0x1f6ae),
    EmojiNCU("potable_water", 0x1f6b0),
    EmojiNCU("accessible", 0x267f),
    EmojiNCU("mens", 0x1f6b9),
    EmojiNCU("womens", 0x1f6ba),
    EmojiNCU("restroom", 0x1f6bb),
    EmojiNCU("baby_change_station", 0x1f6bc),
    EmojiNCU("wc", 0x1f6be),
    EmojiNCU("passport_control", 0x1f6c2),
    EmojiNCU("customs", 0x1f6c3),
    EmojiNCU("baggage_claim", 0x1f6c4),
    EmojiNCU("locker", 0x1f6c5),
    EmojiNCU("children_crossing", 0x1f6b8),
    EmojiNCU("no_entry", 0x26d4),
    EmojiNCU("prohibited", 0x1f6ab),
    EmojiNCU("no_bicycles", 0x1f6b3),
    EmojiNCU("no_smoking", 0x1f6ad),
    EmojiNCU("do_not_litter", 0x1f6af),
    EmojiNCU("non-potable_water", 0x1f6b1),
    EmojiNCU("no_pedestrians", 0x1f6b7),
    EmojiNCU("no_phones", 0x1f4f5),
    EmojiNCU("underage", 0x1f51e),
    EmojiNCU("clockwise", 0x1f503),
    EmojiNCU("counterclockwise", 0x1f504),
    EmojiNCU("back", 0x1f519),
    EmojiNCU("end", 0x1f51a),
    EmojiNCU("on", 0x1f51b),
    EmojiNCU("soon", 0x1f51c),
    EmojiNCU("top", 0x1f51d),
    EmojiNCU("place_of_worship", 0x1f6d0),
    EmojiNCU("om", 0x1f549),
    EmojiNCU("menorah", 0x1f54e),
    EmojiNCU("aries", 0x2648),
    EmojiNCU("taurus", 0x2649),
    EmojiNCU("gemini", 0x264a),
    EmojiNCU("cancer", 0x264b),
    EmojiNCU("leo", 0x264c),
    EmojiNCU("virgo", 0x264d),
    EmojiNCU("libra", 0x264e),
    EmojiNCU("scorpius", 0x264f),
    EmojiNCU("sagittarius", 0x2650),
    EmojiNCU("capricorn", 0x2651),
    EmojiNCU("aquarius", 0x2652),
    EmojiNCU("pisces", 0x2653),
    EmojiNCU("ophiuchus", 0x26ce),
    EmojiNCU("shuffle", 0x1f500),
    EmojiNCU("repeat", 0x1f501),
    EmojiNCU("repeat_one", 0x1f502),
    EmojiNCU("fast_forward", 0x23e9),
    EmojiNCU("rewind", 0x23ea),
    EmojiNCU("upvote", 0x1f53c),
    EmojiNCU("double_up", 0x23eb),
    EmojiNCU("downvote", 0x1f53d),
    EmojiNCU("double_down", 0x23ec),
    EmojiNCU("pause", 0x23f8),
    EmojiNCU("stop_button", 0x23f9),
    EmojiNCU("record", 0x23fa),
    EmojiNCU("cinema", 0x1f3a6),
    EmojiNCU("low_brightness", 0x1f505),
    EmojiNCU("brightness", 0x1f506),
    EmojiNCU("cell_reception", 0x1f4f6),
    EmojiNCU("vibration_mode", 0x1f4f3),
    EmojiNCU("phone_off", 0x1f4f4),
    EmojiNCU("plus", 0x2795),
    EmojiNCU("minus", 0x2796),
    EmojiNCU("division", 0x2797),
    EmojiNCU("question", 0x2753),
    EmojiNCU("grey_question", 0x2754),
    EmojiNCU("grey_exclamation", 0x2755),
    EmojiNCU("exclamation", 0x2757),
    EmojiNCU("exchange", 0x1f4b1),
    EmojiNCU("dollars", 0x1f4b2),
    EmojiNCU("trident", 0x1f531),
    EmojiNCU("name_badge", 0x1f4db),
    EmojiNCU("beginner", 0x1f530),
    EmojiNCU("circle", 0x2b55),
    EmojiNCU("check", 0x2705),
    EmojiNCU("cross_mark", 0x274c),
    EmojiNCU("x", 0x274e),
    EmojiNCU("loop", 0x27b0),
    EmojiNCU("double_loop", 0x27bf),
    EmojiNCU("capital_abcd", 0x1f520),
    EmojiNCU("abcd", 0x1f521),
    EmojiNCU("1234", 0x1f522),
    EmojiNCU("symbols", 0x1f523),
    EmojiNCU("abc", 0x1f524),
    EmojiNCU("ab", 0x1f18e),
    EmojiNCU("cl", 0x1f191),
    EmojiNCU("cool", 0x1f192),
    EmojiNCU("free", 0x1f193),
    EmojiNCU("id", 0x1f194),
    EmojiNCU("new", 0x1f195),
    EmojiNCU("ng", 0x1f196),
    EmojiNCU("squared_ok", 0x1f197),
    EmojiNCU("sos", 0x1f198),
    EmojiNCU("squared_up", 0x1f199),
    EmojiNCU("vs", 0x1f19a),
    EmojiNCU("red_circle", 0x1f534),
    EmojiNCU("blue_circle", 0x1f535),
    EmojiNCU("black_circle", 0x26ab),
    EmojiNCU("white_circle", 0x26aa),
    EmojiNCU("black_large_square", 0x2b1b),
    EmojiNCU("white_large_square", 0x2b1c),
    EmojiNCU("black_medium_small_square", 0x25fe),
    EmojiNCU("white_medium_small_square", 0x25fd),
    EmojiNCU("large_orange_diamond", 0x1f536),
    EmojiNCU("large_blue_diamond", 0x1f537),
    EmojiNCU("small_orange_diamond", 0x1f538),
    EmojiNCU("small_blue_diamond", 0x1f539),
    EmojiNCU("red_triangle_up", 0x1f53a),
    EmojiNCU("red_triangle_down", 0x1f53b),
    EmojiNCU("cute", 0x1f4a0),
    EmojiNCU("radio_button", 0x1f518),
    EmojiNCU("black_and_white_square", 0x1f533),
    EmojiNCU("white_and_black_square", 0x1f532),

// Flags
    EmojiNCU("checkered_flag", 0x1f3c1),
    EmojiNCU("triangular_flag", 0x1f6a9),
    EmojiNCU("crossed_flags", 0x1f38c),
    EmojiNCU("black_flag", 0x1f3f4),
    EmojiNCU("white_flag", 0x1f3f3)
)