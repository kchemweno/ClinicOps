package view.backing;

import javax.faces.context.FacesContext;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichGridCell;
import oracle.adf.view.rich.component.rich.layout.RichGridRow;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGridLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichSpacer;


public class Login {
    private RichForm f1;
    private RichDocument d1;
    private RichPanelStretchLayout psl1;
    private RichPanelGroupLayout pgl1;
    private RichPanelFormLayout pfl1;
    private RichInputText it1;
    private RichInputText it2;
    private RichButton b1;
    private RichSpacer s1;
    private HtmlCommandLink cl1;
    private HtmlCommandLink cl2;
    private RichPanelGridLayout pgl2;
    private RichGridRow gr1;
    private RichGridCell gc1;
    private RichGridCell gc2;

    public void setF1(RichForm f1) {
        this.f1 = f1;
    }

    public RichForm getF1() {
        return f1;
    }

    public void setD1(RichDocument d1) {
        this.d1 = d1;
    }

    public RichDocument getD1() {
        return d1;
    }


    private String username;
    private String password;

    public void setUsername(String _username) {
        this.username = _username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String _password) {
        this.password = _password;
    }

    public String getPassword() {
        return password;
    }

    public String doLogin() {
        // Get an object encapsulating the HTTP request from the ExternalContext
        FacesContext ctx = FacesContext.getCurrentInstance();
        username = it1.getValue().toString();
        password = it2.getValue().toString();
        // Handle an error when credentials have not been provided.
        if (username == null || password == null) {
            showError("Invalid credentials", "An incorrect username or password was specified.", null);
        } else {
            // Get an object encapsulating the HTTP request from the ExternalContext
            ExternalContext ectx = ctx.getExternalContext();
            HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
            try {
                //attempt to log in the user issuing the request using the Servlet
                // 3.0 API login() method.
                //In this programmatic login example, which relies on the Servlet API,
                //Oracle Single Sign-On (Oracle SSO) is not supported.
                request.login(username, password); // Servlet 3.0 login
                username = null;
                password = null;
                //supply a URL to which to forward the user after successful login using
                //the ADF authentication servlet to perform the redirect.
                String loginUrl =
                    ectx.getRequestContextPath() + "/adfAuthentication?success_url=/faces/appointments.jsf";
                redirect(loginUrl);
            } catch (ServletException fle) {
                showError("ServletException", "Login failed. Please verify the username and password and try again.",
                          null);
            }
        }
        return null;
    }

    private void redirect(String forwardUrl) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext ectx = ctx.getExternalContext();
        try {
            ectx.redirect(forwardUrl);
        } catch (IOException ie) {
            showError("IOException", "An error occurred during redirecting. Please consult logs for more information.", ie);
        }
    }

    private void showError(String errType, String message, Exception e) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errType, message);
        FacesContext.getCurrentInstance().addMessage("d2:it35", msg);
        if (e != null) {
            e.printStackTrace();
        }
    }

    //This logoff() method uses the logout() method of Servlet 3.0 API
    //to process a logoff action

    public String logoff() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext ectx = ctx.getExternalContext();
        HttpServletRequest httpRequest = (HttpServletRequest) ectx.getRequest();
        try {
            httpRequest.logout(); // Servlet 3.0 logout
            HttpSession session = httpRequest.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            String logoutUrl = ectx.getRequestContextPath() + "/faces" + ctx.getViewRoot().getViewId();
            redirect(logoutUrl);
        } catch (ServletException e) {
            showError("ServletException", "An error occurred during logout. Please consult logs for more information.",
                      e);
        }
        return null;
    }


    public void setPsl1(RichPanelStretchLayout psl1) {
        this.psl1 = psl1;
    }

    public RichPanelStretchLayout getPsl1() {
        return psl1;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setIt1(RichInputText it1) {
        this.it1 = it1;
    }

    public RichInputText getIt1() {
        return it1;
    }

    public void setIt2(RichInputText it2) {
        this.it2 = it2;
    }

    public RichInputText getIt2() {
        return it2;
    }

    public void setB1(RichButton b1) {
        this.b1 = b1;
    }

    public RichButton getB1() {
        return b1;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void setCl1(HtmlCommandLink cl1) {
        this.cl1 = cl1;
    }

    public HtmlCommandLink getCl1() {
        return cl1;
    }

    public void setCl2(HtmlCommandLink cl2) {
        this.cl2 = cl2;
    }

    public HtmlCommandLink getCl2() {
        return cl2;
    }

    public void setPgl2(RichPanelGridLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGridLayout getPgl2() {
        return pgl2;
    }

    public void setGr1(RichGridRow gr1) {
        this.gr1 = gr1;
    }

    public RichGridRow getGr1() {
        return gr1;
    }

    public void setGc1(RichGridCell gc1) {
        this.gc1 = gc1;
    }

    public RichGridCell getGc1() {
        return gc1;
    }

    public void setGc2(RichGridCell gc2) {
        this.gc2 = gc2;
    }

    public RichGridCell getGc2() {
        return gc2;
    }
}
