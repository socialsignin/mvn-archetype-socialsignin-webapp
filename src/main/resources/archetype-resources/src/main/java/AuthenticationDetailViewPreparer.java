package ${package};

import java.util.HashMap;
import java.util.Map;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationDetailViewPreparer implements ViewPreparer {

	@Autowired
	private ConnectionFactoryRegistry connectionFactoryRegistry;
	
	private String getAuthenticatedUserName() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		return authentication == null ? null : authentication.getName();
	}
	
	
	private Map<String, String> getRegisteredProviderRoleNamesByProviderName() {
		Map<String, String> registeredProviderRoleNamesByProviderName = new HashMap<String, String>();
		for (String registeredProviderId : connectionFactoryRegistry
				.registeredProviderIds()) {
			registeredProviderRoleNamesByProviderName.put(registeredProviderId,
					"ROLE_USER_" + registeredProviderId.toUpperCase());
		}
		return registeredProviderRoleNamesByProviderName;

	}
	
	
	@Override
	public void execute(TilesRequestContext tilesRequestContext, AttributeContext attributeContext) {

		Exception lastSessionException = (Exception)tilesRequestContext.getSessionScope().get("lastSessionException");	
		if (lastSessionException != null)
		{
			tilesRequestContext.getRequestScope().put("exception", lastSessionException);
			tilesRequestContext.getSessionScope().remove("lastSessionException");
		}
		Attribute providerRoleNames = new Attribute(getRegisteredProviderRoleNamesByProviderName());
			Attribute userName = new Attribute(getAuthenticatedUserName());

			attributeContext.putAttribute("registeredProviderRoleNamesByProviderName",providerRoleNames,true);
			attributeContext.putAttribute("userName",userName,true);

	}

}
