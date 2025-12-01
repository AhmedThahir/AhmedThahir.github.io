# HTML

Redirect

```html
<meta http-equiv="refresh" content="0; url=https://www.conductor.com">
```

Auto-refresh page every 5sec

```html
<meta http-equiv="refresh" content="5">
```

## Autocomplete/Suggestions

### Text

```html
<label for="city_input">City</label>
<input type="text" id="city_input" list="cities_list" />
<datalist id="cities_list">
	<option value="Dubai">My city</option>
 	<option value="Kayal">My hometown</option>
</datalist>
```

### Colors

```html
<label for="color_picker">Pick a color</label>
<input type="color" id="color_picker" list="colors_list" />
<datalist id="colors_list">
	<option value="#155AF0">Primary Color</option>
 	<option value="#FFF">Secondary Color</option>
</datalist>
```

![image-20240422165900064](./assets/image-20240422165900064.png){ loading=lazy }
