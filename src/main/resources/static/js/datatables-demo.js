// Call the dataTables jQuery plugin
$(document).ready(function() {
  $('#dataTable').DataTable(
//		  {
//
//	    'columnDefs' : [
//	        //hide the second & fourth column
//	        { 'visible': false, 'targets': [0,5,6] },
//	        { 'searchable': true, 'targets': [0,5,6] }
//	    ]
//  
//   ,"bDestroy": true
//  }
);
  
  
	
////DataTable
//  var table = $('#dataTable').DataTable({
//      initComplete: function () {
//          // Apply the search
//          this.api().columns().every( function () {
//              var that = this;
//
//              $( 'menu_search_text', this.footer() ).on( 'keyup change clear', function () {
//                  if ( that.search() !== this.value ) {
//                      that
//                          .search( this.value )
//                          .draw();
//                  }
//              } );
//          } );
//      }
//  });  
//  
  
  
});
