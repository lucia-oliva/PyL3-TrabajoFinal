# PyL3-TrabajoFinal
# App Java para registrar accidentes

Desarrollada por:
- Caliva Reynoso Dilan Nahuel ELSI911
- Oliva Ana Lucia ELSI842
- Facundo Ruiz ELSI919

Diagrama DER 
![Imagen de WhatsApp 2024-11-09 a las 18 43 27_641d46f0](https://github.com/user-attachments/assets/edcfbd7e-5172-4ba8-bd8a-461722897e95)

```sql
CREATE TABLE Empleado (
    legajo INT PRIMARY KEY,
    apellido_nombre VARCHAR(255) NOT NULL,
    CONSTRAINT legajo_unique CHECK (legajo <> 0)
);

CREATE TABLE Motivo (
    codigo INT PRIMARY KEY,
    motivo VARCHAR(255) NOT NULL,
    CONSTRAINT motivo_length CHECK (LENGTH(motivo) >= 3)
);

CREATE TABLE TipoAccidente (
    codigo INT PRIMARY KEY,
    tipo VARCHAR(255) NOT NULL,
    CONSTRAINT tipo_length CHECK (LENGTH(tipo) >= 3),
    CONSTRAINT tipo_unique UNIQUE (tipo)
);

CREATE TABLE ParteCuerpo (
    codigo INT PRIMARY KEY,
    parte VARCHAR(255) NOT NULL,
    CONSTRAINT parte_length CHECK (LENGTH(parte) >= 3)
);

CREATE TABLE ZonaCuerpo (
    id_zona INT PRIMARY KEY,
    codigo INT NOT NULL,
    izqDer INT NOT NULL,
    CONSTRAINT fk_codigo_ParteCuerpo FOREIGN KEY (codigo) REFERENCES ParteCuerpo (codigo)
);

CREATE TABLE Accidente (
    numero INT PRIMARY KEY,
    fecha_del_accidente DATE NOT NULL,
    ubicacion VARCHAR(255) NOT NULL,
    legajo INT NOT NULL,
    codigo_motivo INT NOT NULL,
    codigo_tipo_accidente INT NOT NULL,
    CONSTRAINT fk_legajo FOREIGN KEY (legajo) REFERENCES Empleado (legajo),
    CONSTRAINT fk_codigo_motivo FOREIGN KEY (codigo_motivo) REFERENCES Motivo (codigo),
    CONSTRAINT fk_codigo_tipo_accidente FOREIGN KEY (codigo_tipo_accidente) REFERENCES TipoAccidente (codigo)
);

CREATE TABLE AccidenteZonaCuerpo (
    numero_accidente INT NOT NULL,
    id_zona INT NOT NULL,
    PRIMARY KEY (numero_accidente, id_zona),
    CONSTRAINT fk_accidente FOREIGN KEY (numero_accidente) REFERENCES Accidente (numero),
    CONSTRAINT fk_zona_cuerpo FOREIGN KEY (id_zona) REFERENCES ZonaCuerpo (id_zona)
);
```

# Estructura del proyecto:
C:.
│   .gitignore
│   README.md
│   
└───TrabajoFinal
    │   .env
    │   .gitignore
    │   pom.xml
    │   
    ├───src
    │   ├───main
    │   │   └───java
    │   │       └───unlar
    │   │           └───edu
    │   │               └───ar
    │   │                   └───paradigma
    │   │                       │   TrabajoFinal.java
    │   │                       │   
    │   │                       ├───controladores
    │   │                       │       AccidenteController.java
    │   │                       │       EmpleadoController.java
    │   │                       │       IABMController.java
    │   │                       │       MotivoController.java
    │   │                       │       ParteCuerpoController.java
    │   │                       │       SetConexion.java
    │   │                       │       TipoAccidenteController.java
    │   │                       │       ZonaCuerpoController.java
    │   │                       │       
    │   │                       ├───gui
    │   │                       │   │   Principal.form
    │   │                       │   │   Principal.java
    │   │                       │   │   
    │   │                       │   └───forms
    │   │                       │       │   FormularioEmpleado.form
    │   │                       │       │   FormularioEmpleado.java
    │   │                       │       │   FormularioMotivo.form
    │   │                       │       │   FormularioMotivo.java
    │   │                       │       │   FormularioParteCuerpo.form
    │   │                       │       │   FormularioParteCuerpo.java
    │   │                       │       │   FormularioTipoAccidente.form
    │   │                       │       │   FormularioTipoAccidente.java
    │   │                       │       │   FormularioZonaCuerpo.form
    │   │                       │       │   FormularioZonaCuerpo.java
    │   │                       │       │   GenericFormAgregar.form
    │   │                       │       │   GenericFormAgregar.java
    │   │                       │       │   GenericFormModificar.form
    │   │                       │       │   GenericFormModificar.java
    │   │                       │       │
    │   │                       │       ├───abm
    │   │                       │       │       AddAccidente.form
    │   │                       │       │       AddAccidente.java
    │   │                       │       │       AddEmpleado.form
    │   │                       │       │       AddEmpleado.java
    │   │                       │       │       AddMotivo.form
    │   │                       │       │       AddMotivo.java
    │   │                       │       │       AddParteCuerpo.form
    │   │                       │       │       AddParteCuerpo.java
    │   │                       │       │       AddTipoAccidente.form
    │   │                       │       │       AddTipoAccidente.java
    │   │                       │       │       AddZonaCuerpo.form
    │   │                       │       │       AddZonaCuerpo.java
    │   │                       │       │       ModEmpleado.form
    │   │                       │       │       ModEmpleado.java
    │   │                       │       │       ModMotivo.form
    │   │                       │       │       ModMotivo.java
    │   │                       │       │       ModParteCuerpo.form
    │   │                       │       │       ModParteCuerpo.java
    │   │                       │       │       ModTipoAccidente.form
    │   │                       │       │       ModTipoAccidente.java
    │   │                       │       │       ModZonaCuerpo.form
    │   │                       │       │       ModZonaCuerpo.java
    │   │                       │       │
    │   │                       │       └───grillas
    │   │                       │               GrillaAccidentes.java
    │   │                       │               GrillaEmpleado.java
    │   │                       │               GrillaMotivo.java
    │   │                       │               GrillaParteCuerpo.java
    │   │                       │               GrillaTipoAccidente.java
    │   │                       │               GrillaZonaCuerpo.java
    │   │                       │
    │   │                       └───objetos
    │   │                               AccidenteDTO.java
    │   │                               Empleado.java
    │   │                               Motivo.java
    │   │                               ParteCuerpo.java
    │   │                               TipoAccidente.java
    │   │                               ZonaCuerpo.java
