package taches;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class TacheConverter implements Converter{

	@Inject  TachesDAO tachesDAO;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
	if(value != null && value.trim().length() > 0) {
        try {
             return tachesDAO.getTache(Integer.parseInt(value));
        } catch(NumberFormatException e) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur de conversion ", "Tache invalide."));
        }
    }
    return null;
}

@Override
public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
	if(value != null) {
        return String.valueOf(((ETache) value).getId());
    }
	return null;
}




}
