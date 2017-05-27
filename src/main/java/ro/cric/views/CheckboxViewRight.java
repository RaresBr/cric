package ro.cric.views;
 
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
 
@ManagedBean 
public class CheckboxViewRight {
 
   
    private List<SelectItem> cars;
    private String[] selectedCars;
 
    @PostConstruct
    public void init() {
         
        cars = new ArrayList<SelectItem>();
        SelectItemGroup germanCars = new SelectItemGroup("Lost people");
        /*germanCars.setSelectItems(new SelectItem[] {
            new SelectItem("BMW", "BMW"),
            new SelectItem("Mercedes", "Mercedes"),
            new SelectItem("Volkswagen", "Volkswagen")
        });
        */
         
        SelectItemGroup americanCars = new SelectItemGroup("Crisis");
        /*americanCars.setSelectItems(new SelectItem[]{
            new SelectItem("Chrysler", "Chrysler"),
            new SelectItem("GM", "GM"),
            new SelectItem("Ford", "Ford")
        });
        */
 
        cars.add(germanCars);
        cars.add(americanCars);
    }
 
    public List<SelectItem> getCars() {
        return cars;
    }
 
    public void setCars(List<SelectItem> cars) {
        this.cars = cars;
    }
 
    public String[] getSelectedCars() {
        return selectedCars;
    }
 
    public void setSelectedCars(String[] selectedCars) {
        this.selectedCars = selectedCars;
    }
}