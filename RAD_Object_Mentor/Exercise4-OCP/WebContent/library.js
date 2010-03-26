function focus_first_form_field() {
	focus_first_field_in_this_form("")
}

function focus_first_field_in_this_form(which_form) {
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
				//alert("elem: id, name: "+elem.id+", "+elem.name)
				elem.focus()
				break
			}
		}
	}
}

function clear_value_on_first_focus(this_object) {
	if (this_object == null) return;
	if (this_object.hasAttribute("first_focus")) return;
	this_object.value = ""
	this_object.setAttribute("first_focus", "false")
} 

function click_or_uncheck_all(this_checkbox) {
	if (this_checkbox == null) return;
	for (var j = 0; j < this_checkbox.form.elements.length; j++) {
		elem = this_checkbox.form.elements[j]
		if (elem.type == 'checkbox' && elem.disabled == false) {
			elem.checked = this_checkbox.checked;
		}
	}
}	
