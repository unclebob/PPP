form_for_load_focus = ""
form_field_for_load_focus = ""

function focus_on_this_form_field(form, field) {
	form_for_load_focus = form
	form_field_for_load_focus = field
}

function focus_first_form_field() {
	focus_first_field_in_this_form("")
}

function focus_first_field_in_this_form(which_form) {
	//alert (form_for_load_focus + ", " + form_field_for_load_focus)
	if (which_form != "") {
		which_form = form_field_for_load_focus
	}
	var forms = document.forms
	var form = null
	for (var i = 0; i < forms.length; i++) {
		form = forms[i]
		if (which_form == "" || forms[i].id == which_form) {
			break
		}
	}
	//alert("form id: "+form.id)
	if (form != null) {
		for (var j = 0; j < form.elements.length; j++) {
			elem = form.elements[j]
			if (elem.type != 'hidden' && elem.disabled == false) {
				if (form_field_for_load_focus == "" ||  form_field_for_load_focus == elem.id) {
					//alert("elem: id, name: "+elem.id+", "+elem.name)
					elem.focus()
					break
				}
			}
		}
	}
}

function clear_value_on_first_focus(this_object, optional_id_prefix) {
	if (this_object == null) return;
	if (this_object.hasAttribute("first_focus")) return;
	this_object.value = ""
	this_object.setAttribute("first_focus", "false")
} 

function select_value_on_first_focus(this_object, optional_id_prefix) {
	if (this_object == null) return;
	if (this_object.hasAttribute("first_focus")) return;
	this_object.select()
	this_object.setAttribute("first_focus", "false")
} 

function click_or_uncheck_all(this_checkbox, optional_id_prefix) {
	if (this_checkbox == null) return;
	for (var j = 0; j < this_checkbox.form.elements.length; j++) {
		elem = this_checkbox.form.elements[j]
		if (elem.type == 'checkbox' && elem.disabled == false) {
			if (optional_id_prefix == null || optional_id_prefix == "" || elem.id.match("^"+optional_id_prefix+".*$")) 
			elem.checked = this_checkbox.checked;
		}
	}
}	
