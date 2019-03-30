/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artec.apps.facebook;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author imstu
 */
public abstract class Parameters {
    
    
    /**
     * @@link https://developers.facebook.com/docs/graph-api/reference/v3.2/insights
     * @return 
     */
    public static List<String> metrics () {
        List<String> metric = new ArrayList<>();
        metric.add("post_impressions_unique"); // default para promedio
        
        metric.add("post_impressions_fan_unique");
        metric.add("post_impressions_organic_unique");
        metric.add("post_impressions_viral_unique");
        metric.add("post_impressions_nonviral_unique");
        metric.add("post_impressions_by_story_type_unique");
        
        metric.add("post_engaged_users");
        
        metric.add("post_negative_feedback_unique");
        metric.add("post_negative_feedback_by_type_unique");
        metric.add("post_clicks_unique");
        metric.add("post_clicks_by_type_unique");
        
        metric.add("post_reactions_like_total");
        metric.add("post_reactions_love_total");
        metric.add("post_reactions_wow_total");
        metric.add("post_reactions_haha_total");
        metric.add("post_reactions_sorry_total");
        metric.add("post_reactions_anger_total");
        return metric;
    }
    
}
