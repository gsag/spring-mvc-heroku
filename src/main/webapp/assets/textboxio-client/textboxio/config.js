var config = {
    css: {
        // Set the editor rendering stylesheets
        stylesheets: ['/assets/textboxio-client/example.css']
    },
    paste: {
        // Override default paste behavior, removing all inline styles
        style: 'clean'
    },
    ui: {
        toolbar: {
            items: ['undo', 'insert', 'style', 'emphasis', 'align', 'listindent', 'format', 'tools']
        }
    }
};
var editor = textboxio.replace('.editableDiv', config);