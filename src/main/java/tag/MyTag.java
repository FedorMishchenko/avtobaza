package tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class MyTag extends TagSupport {
    private static final long serialVersionUID = -454408927785704516L;


    private String massage;

    public void setMassage(String massage){
        this.massage = massage;
    }

    public int doStartTag() {

        try {
            JspWriter jspWriter = pageContext.getOut();
            jspWriter.write(massage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
