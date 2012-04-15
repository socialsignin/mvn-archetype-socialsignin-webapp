/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ${package};

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

#if(${providerList.contains('facebook')})
import org.socialsignin.provider.facebook.FacebookProviderService;
import org.springframework.social.facebook.api.Facebook;
#end
#if(${providerList.contains('lastfm')})
import org.socialsignin.provider.lastfm.LastFmProviderService;
import org.springframework.social.lastfm.api.LastFm;
#end
#if(${providerList.contains('linkedin')})
import org.socialsignin.provider.linkedin.LinkedInProviderService;
import org.springframework.social.linkedin.api.LinkedIn;
#end
#if(${providerList.contains('mixcloud')})
import org.socialsignin.provider.mixcloud.MixcloudProviderService;
import org.springframework.social.mixcloud.api.Mixcloud;
#end
#if(${providerList.contains('soundcloud')})
import org.socialsignin.provider.soundcloud.SoundCloudProviderService;
import org.springframework.social.soundcloud.api.SoundCloud;
#end
#if(${providerList.contains('twitter')})
import org.socialsignin.provider.twitter.TwitterProviderService;
import org.springframework.social.twitter.api.Twitter;
#end
#if(${providerList.contains('tumblr')})
import org.socialsignin.provider.tumblr.TumblrProviderService;
import org.springframework.social.tumblr.api.Tumblr;
import org.springframework.social.tumblr.api.UserInfoBlog;
#end
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SocialSignInShowcaseController {

	@Autowired
	private ConnectionFactoryRegistry connectionFactoryRegistry;
	
	#if(${providerList.contains('lastfm')})
	@Autowired
	private LastFmProviderService lastFmProviderService;
	#end
	
	#if(${providerList.contains('facebook')})
	@Autowired
	private FacebookProviderService facebookProviderService;
	#end
	
	#if(${providerList.contains('twitter')})
	@Autowired
	private TwitterProviderService twitterProviderService;
	#end
	
	#if(${providerList.contains('mixcloud')})
	@Autowired
	private MixcloudProviderService mixcloudProviderService;
	#end
	
	#if(${providerList.contains('soundcloud')})
	@Autowired
	private SoundCloudProviderService soundCloudProviderService;
	#end
	
	#if(${providerList.contains('linkedin')})
	@Autowired
	private LinkedInProviderService linkedInProviderService;
	#end
	
	#if(${providerList.contains('tumblr')})
	@Autowired
	private TumblrProviderService tumblrProviderService;
	#end


	@RequestMapping("/login")
	public String login(Map model) {
		return "oauthlogin";
	}

	@RequestMapping("/connectWithProvider")
	public String connect(Map model) {

		return "oauthconnect";
	}

	@RequestMapping("/")
	public String helloPublicWorld(Map model) {

		return "publicPage";
	}


	@RequestMapping("/protected")
	public String helloProtectedWorld(Map model) {
		List<String> profileUrls = new ArrayList<String>();
		
		#if(${providerList.contains('lastfm')})
		LastFm lastFm = lastFmProviderService.getAuthenticatedApi();
		if (lastFm != null)
		{
			profileUrls.add(lastFm.userOperations().getUserProfile().getUrl());
		}
		#end
		
		#if(${providerList.contains('facebook')})
		Facebook facebook = facebookProviderService.getAuthenticatedApi();
		if (facebook != null)
		{
			profileUrls.add(facebook.userOperations().getUserProfile().getLink());	
		}
		#end
		
		#if(${providerList.contains('twitter')})
		Twitter twitter = twitterProviderService.getAuthenticatedApi();
		if (twitter != null)
		{
			profileUrls.add(twitter.userOperations().getUserProfile().getProfileUrl());
		}
		#end
		
		#if(${providerList.contains('mixcloud')})
		Mixcloud mixcloud = mixcloudProviderService.getAuthenticatedApi();
		if (mixcloud != null)
		{
			profileUrls.add(mixcloud.meOperations().getUserProfile().getUrl());
		}
		#end
		
		#if(${providerList.contains('soundcloud')})
		SoundCloud soundCloud = soundCloudProviderService.getAuthenticatedApi();
		if (soundCloud != null)
		{
			profileUrls.add(soundCloud.meOperations().getUserProfile().getPermalinkUrl());
		}
		#end
		
		#if(${providerList.contains('linkedin')})
		LinkedIn linkedIn = linkedInProviderService.getAuthenticatedApi();
		if (linkedIn != null)
		{
			profileUrls.add(linkedIn.profileOperations().getProfileUrl());
		}
		#end
		
		#if(${providerList.contains('tumblr')})
		Tumblr tumblr = tumblrProviderService.getAuthenticatedApi();
		if (tumblr != null)
		{
			List<UserInfoBlog> blogs = tumblr.userOperations().info().getBlogs();
			if (blogs != null && blogs.size() >0)
			{
				profileUrls.add(blogs.get(0).getUrl());
			}
		}
		#end
			
		model.put("profileUrls",
				profileUrls);
	
		return "protectedPage";
	}

}
