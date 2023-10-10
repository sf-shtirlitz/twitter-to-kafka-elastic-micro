package com.microservices.demo.elastic.query.service.security;

import com.microservices.demo.elastic.query.service.common.model.ElasticQueryServiceRequestModel;
import com.microservices.demo.elastic.query.service.common.model.ElasticQueryServiceResponseModel;
import com.microservices.demo.elastic.query.service.model.ElasticQueryServiceAnalyticsResponseModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class QueryServicePermissionEvaluator implements PermissionEvaluator {

    private static final String SUPER_USER_ROLE = "APP_SUPER_USER_ROLE";

    private final HttpServletRequest httpServletRequest;

    public QueryServicePermissionEvaluator(HttpServletRequest request) {
        this.httpServletRequest = request;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean hasPermission(Authentication authentication,
                                 Object targetDomain,
                                 Object permission) {
        if (isSuperUser()) {
            return true;
        }
        if (targetDomain instanceof ElasticQueryServiceRequestModel) {
            return preAuthorize(authentication, ((ElasticQueryServiceRequestModel) targetDomain).getId(), permission);
        } else if (targetDomain instanceof ResponseEntity || targetDomain == null) {
            if (targetDomain == null) {
                return true;
            }
            ElasticQueryServiceAnalyticsResponseModel responseBody =
                    ((ResponseEntity<ElasticQueryServiceAnalyticsResponseModel>) targetDomain).getBody();
            Objects.requireNonNull(responseBody);
            return postAuthorize(authentication, responseBody.getQueryResponseModels(), permission);
        }
        return false;
    }


    @Override
    public boolean hasPermission(Authentication authentication,
                                 Serializable targetId,
                                 String targetType,
                                 Object permission) {
        if (isSuperUser()) {
            return true;
        }
        if (targetId == null) {
            return false;
        }
        return preAuthorize(authentication, (String) targetId, permission);
    }

    private boolean preAuthorize(Authentication authentication, String id, Object permission) {
        TwitterQueryUser twitterQueryUser = (TwitterQueryUser) authentication.getPrincipal();
        PermissionType userPermission = twitterQueryUser.getPermissions().get(id);
        return hasPermission((String) permission, userPermission);
    }

    private boolean postAuthorize(Authentication authentication,
                                  List<ElasticQueryServiceResponseModel> responseBody,
                                  Object permission) {
        TwitterQueryUser twitterQueryUser = (TwitterQueryUser) authentication.getPrincipal();
        int initialResponseSize = responseBody.size();

        // Create a new list to store filtered response models
        List<ElasticQueryServiceResponseModel> filteredResponse = new ArrayList<>();

        for (ElasticQueryServiceResponseModel responseModel : responseBody) {
            PermissionType userPermission = twitterQueryUser.getPermissions().get(responseModel.getId());
            if (hasPermission((String) permission, userPermission)) {
                filteredResponse.add(responseModel);
            }
        }

        // Update the original list with the filtered results
        responseBody.clear();
        responseBody.addAll(filteredResponse);

        // Check if the size changed and return accordingly
        return responseBody.size() > 0 && initialResponseSize > 0;
    }
/*    private boolean postAuthorize(Authentication authentication,
                                  List<ElasticQueryServiceResponseModel> responseBody,
                                  Object permission) {
        TwitterQueryUser twitterQueryUser = (TwitterQueryUser) authentication.getPrincipal();
        int initialResponseSize = responseBody.size();
        for (ElasticQueryServiceResponseModel responseModel : responseBody) {
            PermissionType userPermission = twitterQueryUser.getPermissions().get(responseModel.getId());
            if (!hasPermission((String) permission, userPermission)) {
                responseBody.remove(responseModel);
                postAuthorize(authentication, responseBody, permission);
                if(responseBody.size()==0 && initialResponseSize > 0) {
                    return false;
                } else return true;
            }
        }
        return true;
    }*/

    private boolean hasPermission(String requiredPermission, PermissionType userPermission) {
        return userPermission != null && requiredPermission.equals(userPermission.getType());
    }

    private boolean isSuperUser() {
        return httpServletRequest.isUserInRole(SUPER_USER_ROLE);
    }

/*    List<ElasticQueryServiceResponseModel> filterResponse(List<ElasticQueryServiceResponseModel> response) {
        TwitterQueryUser twitterQueryUser = (TwitterQueryUser) authentication.getPrincipal();
        for (ElasticQueryServiceResponseModel responseModel : responseBody) {
            PermissionType userPermission = twitterQueryUser.getPermissions().get(responseModel.getId());
            if (!hasPermission((String) permission, userPermission)) {
                return false;
            }
        }

    }*/
}
