package ro.cric.views;
 
import java.util.List;

import javax.faces.bean.ManagedBean;
 
@ManagedBean (name="selectManyView")
public class SelectManyView {
     
    private List<String> selectedOptions;
     
    public List<String> getSelectedOptions() {
        return selectedOptions;
    }
 
    public void setSelectedOptions(List<String> selectedOptions) {
        this.selectedOptions = selectedOptions;
    }
}