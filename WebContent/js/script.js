$( function() {
    $( "#slider-range" ).slider({
      range: true,
      min: 0,
      max: 50000000,
      values: [ 40000, 10000000 ],
      slide: function( event, ui ) {
        $( "#amount" ).val( "RS." + ui.values[ 0 ] + " - RS." + ui.values[ 1 ] );
      }
    });
    $( "#amount" ).val( "RS." + $( "#slider-range" ).slider( "values", 0 ) +
      " - RS." + $( "#slider-range" ).slider( "values", 1 ) );
  } );