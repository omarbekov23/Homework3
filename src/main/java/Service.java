import java.util.List;

public interface Service {
        void changeDriver(List<AutoPark> list1, List<DriverInfo> list2, int id) ;
        void startDriving(List<AutoPark> list1, List<DriverInfo> list2, int id);
        void startRepair(List<AutoPark> list1, List<DriverInfo> list2, int id);
}
