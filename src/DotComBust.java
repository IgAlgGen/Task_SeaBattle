import java.util.ArrayList;

public class DotComBust {
    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComList = new ArrayList<>();
    private int numOfGuesses = 0;

    private void setUpGame() {
        // Создадим несколько "сайтов" и присвоим им адреса
        DotCom one = new DotCom();
        one.setName("Pets.com");
        DotCom two = new DotCom();
        two.setName("eToys.com");
        DotCom three = new DotCom();
        three.setName("Go2.com");
        dotComList.add(one);
        dotComList.add(two);
        dotComList.add(three);

        System.out.println("Ваша цель - потопить три сайта");
        System.out.println("Pets.com, eToys.com, Go2.com");
        System.out.println("Попытайтесь потопить их за минимальное количество ходов");

        for(DotCom dotCom: dotComList){
            ArrayList<String> newLocatuon = helper.plaseDotCom(3);
            dotCom.setLocationCells(newLocatuon);
        }
    }
    private void starPlaying (){
        while (!dotComList.isEmpty()){
            String userGuess = helper.getUserInput("Сделайе ход");
            checkUserGuess(userGuess);
        }
        finishGame();
    }

    private void checkUserGuess(String userGuess) {
        numOfGuesses++;
        String result = "Мимо";
        for(DotCom dotComToTest: dotComList){
            result = dotComToTest.checkYourself(userGuess);
            if (result.equals("Попал")){
                break;
            }
            if (result.equals("Потопил")){
                dotComList.remove(dotComToTest);
                break;
            }
        }
        System.out.println(result);
    }

    private void finishGame(){
        System.out.println("Все сайты ушли ко дну!");
        if (numOfGuesses <=18){
            System.out.println("Это заняло у вас всего "+numOfGuesses+ " попыток");
        }else {
            System.out.println("Это заняло у вас довольно много времени " + numOfGuesses+ " попыток");
        }
    }

    public static void main(String[] args) {
        DotComBust game = new DotComBust();
        game.setUpGame();
        game.starPlaying();
    }
}