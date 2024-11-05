from flask import Blueprint, abort, request, render_template, redirect
import json
import requests
from flask import flash
router = Blueprint('router', __name__)

@router.route('/')
def home():
    return render_template('template.html')

##Proyecto routes
@router.route('/misproyecto')
def misproyecto():
    r = requests.get('http://localhost:8099/api/proyectos/misproyectos')
    data = r.json()
    return render_template('misproyectos/lista.html', lista = data["data"])

## Guardar el proyecto 
@router.route('/misproyecto/guardar', methods=['POST'])
def guadarproyecto():
    hearders = {'Content-Type': 'application/json'}
    form = request.form

    dataF = {
        "nombreProyecto": form['np'],
        "inversion": form['in'],
        "tiempoVida": form['tV'],
        "fechaInicio": form['fI'],
        "fechaFin": form['fF'],
        "electricidadGenerada": form['eg'],
        "costoTotal": form['ct'],
        "codigodelproyecto": form['cp'],
    }
    r = requests.post('http://localhost:8099/api/proyectos/crear', headers=hearders, data=json.dumps(dataF))
    dat = r.json()
    if r.status_code == 200:
        flash("Se guardo correctamente", category='info')
        return redirect('/misproyecto')
    else:
        flash(str(dat["message"]), category='error')
        return redirect('/misproyecto')
    


@router.route('/edicion', methods=['POST'])
def editarmisproyecto():
    hearders = {'Content-Type': 'application/json'}
    form = request.form

    dataF = {
        "idProyecto": form['id'],	
        "nombreProyecto": form['np'],
        "inversion": form['in'],
        "tiempoVida": form['tV'],
        "fechaInicio": form['fI'],
        "fechaFin": form['fF'],
        "electricidadGenerada": form['eg'],
        "costoTotal": form['ct'],
        "codigodelproyecto": form['cp'],
    }
    r = requests.post('http://localhost:8099/api/proyectos/edicion', headers=hearders, data=json.dumps(dataF))
    dat = r.json()
    if r.status_code == 200:
        flash("Se editado correctamente", category='info')
        return redirect('/misproyecto')
    else:
        flash(str(dat["message"]), category='error')
        return redirect('/misproyecto')   
##Vista del guardar proyecto
@router.route('/vista')
def vistaproyecto():
    return render_template('misproyectos/crear.html')

##llamar para editar el proyecto
@router.route('/edicion/<id>')
def edicionproyecto(id):
    r = requests.get('http://localhost:8099/api/proyectos/misproyectos/'+id)
    data = r.json();
    if(r.status_code == 200):
        return render_template('misproyectos/edicion.html', lista = data["data"])
    else:
        flash(data["data"],"Error al cargar la pagina", category='error')
        return redirect('/misproyecto')  

##Editar el proyecto 


    

@router.route('/lista/inversionistas/<codigodelproyecto>')
def listainver(codigodelproyecto):
    r = requests.get('http://localhost:8099/api/inversionistas/misinversionistas/'+codigodelproyecto)
    data = r.json()
    r = requests.get('http://localhost:8099/api/proyectos/misproyectos')
    datah = r.json()
    misproyecto_data = next((proyecto for proyecto in datah["data"] if proyecto["codigodelproyecto"] == codigodelproyecto), None)
    return render_template('misinversionista/lista.html', lista = data["data"], listag = datah["data"], misproyectos = misproyecto_data)

@router.route('/form/inversionistas/<codigodelproyecto>')
def formInver(codigodelproyecto):
    r = requests.get('http://localhost:8099/api/inversionistas/misinversionistas/'+codigodelproyecto)
    data = r.json()
    r = requests.get('http://localhost:8099/api/proyectos/misproyectos')
    datah = r.json()
    misproyecto_data = next((proyecto for proyecto in datah["data"] if proyecto["codigodelproyecto"] == codigodelproyecto), None)
    return render_template('misinversionista/crear.html', lista = data["data"], listag = datah["data"], misproyectos = misproyecto_data)

@router.route('/misinversionistas/<codigodelproyecto>')
def misinversionistas(codigodelproyecto):
    r = requests.get('http://localhost:8099/api/inversionistas/misinversionistas/'+codigodelproyecto)
    data = r.json()
    r = requests.get('http://localhost:8099/api/proyectos/misproyectos')
    datah = r.json()
    misproyecto_data = next((proyecto for proyecto in datah["data"] if proyecto["codigodelproyecto"] == codigodelproyecto), None)
    return render_template('templateinve.html', lista = data["data"], listag = datah["data"], misproyectos = misproyecto_data)

@router.route('/guardar/inversionista/<codigodelproyecto>', methods=['POST'])
def guardarInver(codigodelproyecto):
    hearders = {'Content-Type': 'application/json'}
    form = request.form

    dataF = {
        "nombres": form['nombre'],
        "dni": form['dni'],
    }
    r = requests.post(f'http://localhost:8099/api/inversionistas/crear/{codigodelproyecto}', headers=hearders, data=json.dumps(dataF))
    dat = r.json()
    if r.status_code == 200:
        flash("Se guardo correctamente", category='info')
        return redirect('/lista/inversionistas/'+codigodelproyecto)
    else:
        flash(str(dat["message"]), category='error')
        return redirect('/lista/inversionistas/'+codigodelproyecto)