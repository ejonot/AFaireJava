package taches;


import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

@Named
@ApplicationScoped
public class CategorieConverter implements Converter {
	@Inject  TachesDAO tachesDAO;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		if(value != null && value.trim().length() > 0) {
            try {
                 return tachesDAO.getCategorie(Integer.parseInt(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur de conversion ", "Catégorie invalide."));
            }
        }
        return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
		if(value != null) {
            return String.valueOf(((Categorie) value).getId());
        }
		return null;
	}


}
