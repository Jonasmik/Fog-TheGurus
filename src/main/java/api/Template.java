package api;

import api.facades.TemplateFacade;

public class Template {

    private final TemplateFacade templateFacade;

    public Template(TemplateFacade templateFacade) {
        this.templateFacade = templateFacade;
    }

    public TemplateFacade getTemplateFacade() {
        return templateFacade;
    }
}
