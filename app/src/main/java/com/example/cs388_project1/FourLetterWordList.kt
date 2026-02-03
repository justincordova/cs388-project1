// author: calren
object FourLetterWordList {
    val fourLetterWords =
        "Area,Army,Baby,Back,Ball,Band,Bank,Base,Bill,Body,Book,Call,Card,Care,Case,Cash,City,Club,Cost,Date,Deal,Door,Duty,East,Edge,Face,Fact,Farm,Fear,File,Film,Fire,Firm,Fish,Food,Foot,Form,Fund,Game,Girl,Goal,Gold,Hair,Half,Hall,Hand,Head,Help,Hill,Home,Hope,Hour,Idea,Jack,John,Kind,King,Lack,Lady,Land,Life,Line,List,Look,Lord,Loss,Love,Mark,Mary,Mind,Miss,Move,Name,Need,News,Note,Page,Pain,Pair,Park,Part,Past,Path,Paul,Plan,Play,Post,Race,Rain,Rate,Rest,Rise,Risk,Road,Rock,Role,Room,Rule,Sale,Seat,Shop,Show,Side,Sign,Site,Size,Skin,Sort,Star,Step,Task,Team,Term,Test,Text,Time,Tour,Town,Tree,Turn,Type,Unit,User,View,Wall,Week,West,Wife,Will,Wind,Wine,Wood,Word,Work,Year,Bear,Beat,Blow,Burn,Call,Care,Cast,Come,Cook,Cope,Cost,Dare,Deal,Deny,Draw,Drop,Earn,Face,Fail,Fall,Fear,Feel,Fill,Find,Form,Gain,Give,Grow,Hang,Hate,Have,Head,Hear,Help,Hide,Hold,Hope,Hurt,Join,Jump,Keep,Kill,Know,Land,Last,Lead,Lend,Lift,Like,Link,Live,Look,Lose,Love,Make,Mark,Meet,Mind,Miss,Move,Must,Name,Need,Note,Open,Pass,Pick,Plan,Play,Pray,Pull,Push,Read,Rely,Rest,Ride,Ring,Rise,Risk,Roll,Rule,Save,Seek,Seem,Sell,Send,Shed,Show,Shut,Sign,Sing,Slip,Sort,Stay,Step,Stop,Suit,Take,Talk,Tell,Tend,Test,Turn,Vary,View,Vote,Wait,Wake,Walk,Want,Warn,Wash,Wear,Will,Wish,Work,Able,Back,Bare,Bass,Blue,Bold,Busy,Calm,Cold,Cool,Damp,Dark,Dead,Deaf,Dear,Deep,Dual,Dull,Dumb,Easy,Evil,Fair,Fast,Fine,Firm,Flat,Fond,Foul,Free,Full,Glad,Good,Grey,Grim,Half,Hard,Head,High,Holy,Huge,Just,Keen,Kind,Last,Late,Lazy,Like,Live,Lone,Long,Loud,Main,Male,Mass,Mean,Mere,Mild,Nazi,Near,Neat,Next,Nice,Okay,Only,Open,Oral,Pale,Past,Pink,Poor,Pure,Rare,Real,Rear,Rich,Rude,Safe,Same,Sick,Slim,Slow,Soft,Sole,Sore,Sure,Tall,Then,Thin,Tidy,Tiny,Tory,Ugly,Vain,Vast,Very,Vice,Warm,Wary,Weak,Wide,Wild,Wise,Zero,Ably,Afar,Anew,Away,Back,Dead,Deep,Down,Duly,Easy,Else,Even,Ever,Fair,Fast,Flat,Full,Good,Half,Hard,Here,High,Home,Idly,Just,Late,Like,Live,Long,Loud,Much,Near,Nice,Okay,Once,Only,Over,Part,Past,Real,Slow,Solo,Soon,Sure,T..."

    private val easyWords =
        "Bird,Cool,Dark,Dogs,Dream,Fish,Fire,Game,Giga,Gold,Good,Hairy,Hate,Head,Help,High,Holy,Home,Huge,Jump,Keep,King,Kiss,Lake,Lamp,Late,Leaf,Left,Like,Lion,Live,Love,Luck,Lunch,Magic,Main,Mate,Mega,Moon,Much,Name,Near,Next,Nice,Node,None,Okay,Once,Pain,Park,Pass,Peek,Play,Pull,Push,Quit,Rain,Read,Real,Rest,Rice,Ride,Ring,Risk,Road,Role,Roll,Rope,Rose,Round,Rule,Rush,Save,Seat,Seek,Send,Shop,Show,Shut,Side,Sing,Site,Skin,Skip,Slip,Slow,Smile,Sort,Stay,Stop,Suit,Take,Tall,Team,Test,Tera,Text,That,This,Time,Tiny,Took,Tree,Turn,Type,Unit,User,Very,View,Wake,Walk,Warm,Wash,Wave,Weak,Week,West,Wild,Wind,Wish,Wood,Word,Work,Zero,Zone,Baby,Back,Ball,Band,Bank,Base,Bill,Body,Book,Call,Card,Care,Case,Cash,City,Club,Cost,Date,Deal,Door,Duty,East,Edge,Face,Fact,Farm,Fear,File,Film,Fire,Firm,Fish,Food,Foot,Form,Fund,Girl,Goal,Grow,Hair,Half,Hall,Hand,Help,Hill,Hope,Hour,Jack,John,Kind,Lack,Lady,Land,Life,Line,List,Look,Lord,Loss,Mark,Mary,Mind,Move,Name,News,Note,Page,Pain,Pair,Park,Part,Path,Paul,Plan,Post,Race,Rain,Rate,Rest,Rise,Risk,Road,Rock,Role,Room,Rule,Sale,Seat,Shop,Show,Side,Sign,Site,Size,Skin,Sort,Star,Step,Task,Team,Term,Test,Text,Time,Tour,Town,Tree,Turn,Type,Unit,View,Wall,Week,West,Wife,Will,Wind,Wine,Wood,Work,Year"

    private val hardWords =
        "Abyss,Awk,Barb,Buzz,Byrl,Cwm,Cynic,Djinn,Embe,Enuf,Fjord,Flyby,Frizz,Glits,Gnat,Gymp,Hymn,Jazz,Jump,Kayak,Kvetch,Myth,Nth,Oxy,Phiz,Pfft,Qats,Qoph,Quiz,Rythm,Spyt,Squin,Squiz,Synth,Tjant,Tzatz,Vamp,View,Veldt,Vext,Vughn,Wasp,Waxy,Whup,Wuzz,Xray,Ygrec,Zzzz,Zzyzx"

    fun getAllFourLetterWords(): List<String> {
        return fourLetterWords.split(",")
    }

    fun getRandomFourLetterWord(mode: Int = 0): String {
        val allWords = when (mode) {
            0 -> fourLetterWords
            1 -> easyWords
            2 -> hardWords
            else -> fourLetterWords
        }
        val wordList = allWords.split(",")
        val randomNumber = (0 until wordList.size).random()
        return wordList[randomNumber].uppercase()
    }
}
