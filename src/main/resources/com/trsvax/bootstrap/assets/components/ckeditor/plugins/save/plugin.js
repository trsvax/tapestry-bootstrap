/**
 * Basic sample plugin inserting current date and time into CKEditor editing area.
 *
 * Created out of the CKEditor Plugin SDK:
 * http://docs.ckeditor.com/#!/guide/plugin_sdk_intro
 */

// Register the plugin within the editor.
CKEDITOR.plugins.add( 'save', {

	// Register the icons. They must match command names.
	icons: 'save',

	// The plugin initialization logic goes inside this method.
	init: function( editor ) {

		// Define an editor command that inserts a timestamp.
		editor.addCommand( 'save', {

			// Define the function that will be fired when the command is executed.
			exec: function( editor ) {
				var r = new XMLHttpRequest();
				r.open("PUT", editor.container.$.dataset.url, true);
				r.onreadystatechange = function () {
				  if (r.readyState != 4 || r.status == 200) return;
				  alert("Failed: " + r.status);
				};
				r.send(editor.getData());
			}
		});

		// Create the toolbar button that executes the above command.
		editor.ui.addButton( 'save', {
			label: 'Save',
			command: 'save',
			toolbar: 'document'
		});
	}
});