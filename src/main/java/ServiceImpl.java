import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ServiceImpl implements Service{
    Random random = new Random();
    public static final String ЧЕРНЫЙ = "\u001B[35m";
    public static final String ПЕРЕГРУЗИТЬ = "\u001B[0m";
    public static final String КРАСНЫЙ = "\u001B[31m";
    List<AutoPark> list1 = new ArrayList<AutoPark>();
    List<DriverInfo> list2 = new ArrayList<DriverInfo>();
    private int id;
    @Override
    public void changeDriver(List<AutoPark> list1, List<DriverInfo> list2, int id) {
        this.list1 = list1;
        this.list2 = list2;
        this.id = id;


        if(list1.get(id).getState().equals("Маршрут")) {
            System.out.println(КРАСНЫЙ + "Мы не можем сменить водителя, потому что он уже в пути!" + ПЕРЕГРУЗИТЬ);

        }

        else if(list1.get(id).getDriver().equals("")) {
            for (int i = 0; i < list1.size(); i++) {
                if (list2.get(i).getBus().equals("")) {
                    list1.get(id).setDriver(list2.get(i).getName());
                    list2.get(i).setBus(list1.get(id).getName());
                    System.out.println(ЧЕРНЫЙ +"Водитель успешно назначен."+ ПЕРЕГРУЗИТЬ);
                    break;
                }
            }
        }

        else if(!list1.get(id).getDriver().equals("")){
            for(int i = 0; i<list1.size(); i++){
                if(list1.get(id).getDriver().equals(list2.get(i).getName())){
                    list2.get(i).setBus("");
                    continue;
                }
                if(list2.get(i).getBus().equals("")){
                    list1.get(id).setDriver(list2.get(i).getName());
                    list2.get(i).setBus(list1.get(id).getName());
                    System.out.println(ЧЕРНЫЙ +"Водитель успешно назначен."+ ПЕРЕГРУЗИТЬ);
                    break;

                }
            }
        }
        else if(list1.get(id).getState().equals("РЕМОНТ")) {
            System.out.println(КРАСНЫЙ + "Мы не можем назначить или изменить водителя." + ПЕРЕГРУЗИТЬ);
        }
    }

    public void startDriving(List<AutoPark> list1, List<DriverInfo> list2, int id) {
        int k = random.nextInt(3-1)+1;
        //if already on the road
        if (list1.get(id).getState().equals("МАРШРУТ")){
            System.out.println(КРАСНЫЙ +"Грузовик уже на дороге."+ ПЕРЕГРУЗИТЬ);
        }
        //if under repair and need to change state
        else if(list1.get(id).getState().equals("РЕМОНТ")&&!list1.get(id).getDriver().equals("")){
            if(k==1){
                list1.get(id).setState("МАРШРУТ");
                System.out.println(ЧЕРНЫЙ +"От ремонтировали готов выехать на дорогу."+ ПЕРЕГРУЗИТЬ);
            }
            else {
                list1.get(id).setState("База");
                System.out.println(ЧЕРНЫЙ +"с ремонта выехал починили."+ ПЕРЕГРУЗИТЬ);
            }
        }
        else if(list1.get(id).getState().equals("Ремот")&&list1.get(id).getDriver().equals("")){
            if(k==2){
                list1.get(id).setState("База");
                System.out.println(ЧЕРНЫЙ +"на базе отремонтировали."+ ПЕРЕГРУЗИТЬ);

            }
        }

        else if(list1.get(id).getState().equals("База")&&list1.get(id).getDriver().equals(""))
        {
            System.out.println(КРАСНЫЙ +"Вы не можете начать движение без водителя\n"+ ПЕРЕГРУЗИТЬ);
        }
        else if(!list1.get(id).getDriver().equals("")){
            list1.get(id).setState("МАРШРУТ");
            System.out.println(ЧЕРНЫЙ +"Успешно начал вождение "+ ПЕРЕГРУЗИТЬ);
        }
    }
    @Override
    public void startRepair (List < AutoPark > list1, List < DriverInfo > list2,int id){
        switch (list1.get(id).getState()) {
            case "МАРШРУТ" -> {
                list1.get(id).setState("РЕМОНТ");
                System.out.println(ЧЕРНЫЙ + "Успешно изменен маршрут на ремонт." + ЧЕРНЫЙ);
            }
            case "<База" -> {
                list1.get(id).setState("РЕМОНТ");
                System.out.println(ЧЕРНЫЙ + "Успешно выехал с базы на ремонт." + ЧЕРНЫЙ);
            }
            case "Ремонт" -> System.out.println(КРАСНЫЙ +"Этот фура уже в ремонте."+ ПЕРЕГРУЗИТЬ);
        }

    }
    public List<AutoPark> getList1(){
        return list1;
    }
    public List<DriverInfo> getList2(){
        return list2;
    }
}
