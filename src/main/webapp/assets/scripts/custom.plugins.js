/**
 * Created by guilherme on 22/08/16.
 * Custom plugins
 */

$(document).ready(function() {
    "use strict";

    /**
     * Script to load the first partial page at /account/profile
     */
    (function(){$("#profile-content-partial").load("/account/profile/info");})();
});