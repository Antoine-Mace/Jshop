// Control the spinning gears
$(document).ready(function() {
    // Get the container position
    var containerPos = $('#container').offset();
    
    // Get the initial scroll position. This will be needed later when determining
    // if we are scrolling up or down.
    var scrollPos = $(window).scrollTop();
    var degreeRotate = 0;
    
    // We will use these to track how much we are rotating our gears. Need to track
    // the gears separately since they will not be going the same direction
    var gear1Rotate = 0;
    var gear2Rotate = 0;
    var gear3Rotate = 0;
    
    browser_transform('#gear2', 11);
    browser_transform('#gear3', 90);
    
    $(document).scroll(function() {
        // Are we moving up or down?
        var newScroll = $(window).scrollTop();
        
        if (scrollPos > newScroll) {
            degreeRotate -= 5;
        } else {
            degreeRotate += 5;
        }
        
        // Calculate rotations. These will be slightly different for each gear, even
        // for the ones spinning the same direction, in order to line up the teeth of
        // the gears.
        gear1Rotate = degreeRotate;
        gear2Rotate = ((degreeRotate + 11) * -1);
        gear3Rotate = ((degreeRotate + 90) * -1);
        
        // Store the current scroll for comparison next scroll event.
        scrollPos = newScroll;
        
        browser_transform('#gear1', gear1Rotate);
        browser_transform('#gear2', gear2Rotate);
        browser_transform('#gear3', gear3Rotate);
    });
});

// Handle automatic output of multiple vendor tags for css3 transforms
function browser_transform(transTarget, transValue)
{
    $(transTarget).css('-ms-transform', 'rotate(' + transValue + 'deg)');
    $(transTarget).css('-moz-transform', 'rotate(' + transValue + 'deg)');
    $(transTarget).css('-webkit-transform', 'rotate(' + transValue + 'deg)');
    $(transTarget).css('-o-transform', 'rotate(' + transValue + 'deg)');
    $(transTarget).css('transform', 'rotate(' + transValue + 'deg)');
}