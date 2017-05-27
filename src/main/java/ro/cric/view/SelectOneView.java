package ro.cric.view;
 
import javax.faces.bean.ManagedBean;
 
@ManagedBean
public class SelectOneView {
     
    private String option;
     
    public String getOption() {
        return option;
    }
 
    public void setOption(String option) {
        this.option = option;
    }
}