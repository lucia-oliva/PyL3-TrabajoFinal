package unlar.edu.ar.paradigma;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import unlar.edu.ar.paradigma.controladores.AccidenteController;
import unlar.edu.ar.paradigma.controladores.EmpleadoController;
import unlar.edu.ar.paradigma.controladores.MotivoController;
import unlar.edu.ar.paradigma.controladores.TipoAccidenteController;
import unlar.edu.ar.paradigma.controladores.ParteCuerpoController;
import unlar.edu.ar.paradigma.controladores.ZonaCuerpoController;
import unlar.edu.ar.paradigma.controladores.SetConexion;
import unlar.edu.ar.paradigma.objetos.AccidenteDTO;
import unlar.edu.ar.paradigma.objetos.Empleado;
import unlar.edu.ar.paradigma.objetos.Motivo;
import unlar.edu.ar.paradigma.objetos.TipoAccidente;
import unlar.edu.ar.paradigma.objetos.ParteCuerpo;
import unlar.edu.ar.paradigma.objetos.ZonaCuerpo;

public class TrabajoFinal {

    public static void main(String[] args) throws SQLException {

        try {
            Connection connection = SetConexion.getConnection();
            if (connection != null) {
                probarConexion();

                // Pruebas AccidenteController
                AccidenteController accidenteController = new AccidenteController();
                accidenteController.setConexion(connection);
                // probarExtraer(accidenteController);
                probarExtraerTodo(accidenteController);
                // probarModificar(accidenteController);
                // probarEliminar(accidenteController);
                // probarCrear(accidenteController);

                // Pruebas EmpleadoController
                EmpleadoController empleadoController = new EmpleadoController();
                empleadoController.setConexion(connection);
                // probarExtraerEmpleado(empleadoController);
                probarExtraerTodoEmpleado(empleadoController);
                // probarModificarEmpleado(empleadoController);
                // probarEliminarEmpleado(empleadoController);
                // probarCrearEmpleado(empleadoController);

                // Pruebas MotivoController
                MotivoController motivoController = new MotivoController();
                motivoController.setConexion(connection);
                // probarExtraerMotivo(motivoController);
                probarExtraerTodoMotivo(motivoController);
                // probarModificarMotivo(motivoController);
                // probarEliminarMotivo(motivoController);
                // probarCrearMotivo(motivoController);

                // Pruebas TipoAccidenteController
                TipoAccidenteController tipoAccidenteController = new TipoAccidenteController();
                tipoAccidenteController.setConexion(connection);
                // probarExtraerTipoAccidente(tipoAccidenteController);
                probarExtraerTodoTipoAccidente(tipoAccidenteController);
                // probarModificarTipoAccidente(tipoAccidenteController);
                // probarEliminarTipoAccidente(tipoAccidenteController);
                // probarCrearTipoAccidente(tipoAccidenteController);

                // Pruebas ParteCuerpoController
                ParteCuerpoController parteCuerpoController = new ParteCuerpoController();
                parteCuerpoController.setConexion(connection);
                // probarExtraerParteCuerpo(parteCuerpoController);
                probarExtraerTodoParteCuerpo(parteCuerpoController);
                // probarModificarParteCuerpo(parteCuerpoController);
                // probarEliminarParteCuerpo(parteCuerpoController);
                // probarCrearParteCuerpo(parteCuerpoController);

                // Pruebas ZonaCuerpoController
                ZonaCuerpoController zonaCuerpoController = new ZonaCuerpoController();
                zonaCuerpoController.setConexion(connection);
                // probarExtraerZonaCuerpo(zonaCuerpoController);
                probarExtraerTodoZonaCuerpo(zonaCuerpoController);
                // probarModificarZonaCuerpo(zonaCuerpoController);
                // probarEliminarZonaCuerpo(zonaCuerpoController);
                // probarCrearZonaCuerpo(zonaCuerpoController);
            }

        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error en la creación del accidente: " + e.getMessage());
        }
    }

    private static void probarConexion() {
        System.out.println("Conexion exitosa a la base de datos.");
    }

    // Pruebas para Accidente
    private static void probarExtraer(AccidenteController accidenteController) {
        int idAccidente = 1;
        AccidenteDTO accidente = accidenteController.extraer(idAccidente);
        if (accidente != null) {
            System.out.println("\nAccidente con ID " + idAccidente + ":");
            System.out.println(accidente);
        } else {
            System.out.println("No se encontró un accidente con el ID " + idAccidente);
        }
    }

    private static void probarExtraerTodo(AccidenteController accidenteController) {
        List<AccidenteDTO> listaAccidentes = accidenteController.extraerTodo();
        if (!listaAccidentes.isEmpty()) {
            System.out.println("\nLista de accidentes:");
            for (AccidenteDTO accident : listaAccidentes) {
                System.out.println(accident);
            }
        } else {
            System.out.println("No se encontraron accidentes.");
        }
    }

    private static void probarModificar(AccidenteController accidenteController) {
        AccidenteDTO accidenteAModificar = accidenteController.extraer(1);
        if (accidenteAModificar != null) {
            accidenteAModificar.setUbicacion("Nueva ubicación");
            accidenteAModificar.setFecha_del_accidente(new java.sql.Date(System.currentTimeMillis()));
            accidenteAModificar.setLegajo(1);
            accidenteAModificar.setCodigo_motivo(2);
            accidenteAModificar.setCodigo_tipo_accidente(2);
            accidenteAModificar.setId_zona(1);
            accidenteAModificar.setCodigo(3);

            if (accidenteController.modificar(accidenteAModificar)) {
                System.out.println("Accidente modificado con exito.");
            } else {
                System.out.println("Error al modificar accidente.");
            }
        } else {
            System.out.println("No se encontró el accidente a modificar.");
        }
    }

    private static void probarEliminar(AccidenteController accidenteController) {
        int idAccidente = 3;
        AccidenteDTO accidenteAEliminar = accidenteController.extraer(idAccidente);
        if (accidenteAEliminar != null) {
            if (accidenteController.eliminar(accidenteAEliminar)) {
                System.out.println("Accidente eliminado con exito.");
            } else {
                System.out.println("Error al eliminar accidente.");
            }
        } else {
            System.out.println("No se encontró el accidente con ID " + idAccidente + " para eliminar.");
        }
    }

    private static void probarCrear(AccidenteController accidenteController) {
        AccidenteDTO nuevoAccidente = new AccidenteDTO(
                4,
                new java.sql.Date(System.currentTimeMillis()),
                "Nueva ubicación",
                1,
                1,
                1,
                1,
                1);

        if (accidenteController.crear(nuevoAccidente)) {
            System.out.println("Accidente creado con exito.");
        } else {
            System.out.println("Error al crear accidente.");
        }
    }

    // Pruebas para Empleado
    private static void probarExtraerEmpleado(EmpleadoController empleadoController) {
        int idEmpleado = 1;
        Empleado empleado = empleadoController.extraer(idEmpleado);
        if (empleado != null) {
            System.out.println("\nEmpleado con legajo " + idEmpleado + ":");
            System.out.println(empleado);
        } else {
            System.out.println("No se encontró un empleado con el legajo " + idEmpleado);
        }
    }

    private static void probarExtraerTodoEmpleado(EmpleadoController empleadoController) {
        List<Empleado> listaEmpleados = empleadoController.extraerTodo();
        if (!listaEmpleados.isEmpty()) {
            System.out.println("\nLista de empleados:");
            for (Empleado empleado : listaEmpleados) {
                System.out.println(empleado);
            }
        } else {
            System.out.println("No se encontraron empleados.");
        }
    }

    private static void probarModificarEmpleado(EmpleadoController empleadoController) {
        Empleado empleadoAModificar = empleadoController.extraer(1);
        if (empleadoAModificar != null) {
            empleadoAModificar.setApellido_nombre("Nuevo Nombre");
            if (empleadoController.modificar(empleadoAModificar)) {
                System.out.println("Empleado modificado con exito.");
            } else {
                System.out.println("Error al modificar empleado.");
            }
        } else {
            System.out.println("No se encontró el empleado a modificar.");
        }
    }

    private static void probarEliminarEmpleado(EmpleadoController empleadoController) {
        int idEmpleado = 2;
        Empleado empleadoAEliminar = empleadoController.extraer(idEmpleado);
        if (empleadoAEliminar != null) {
            if (empleadoController.eliminar(empleadoAEliminar)) {
                System.out.println("Empleado eliminado con exito.");
            } else {
                System.out.println("Error al eliminar empleado.");
            }
        } else {
            System.out.println("No se encontró el empleado con ID " + idEmpleado + " para eliminar.");
        }
    }

    private static void probarCrearEmpleado(EmpleadoController empleadoController) {
        Empleado nuevoEmpleado = new Empleado(5, "Nuevo Empleado");
        if (empleadoController.crear(nuevoEmpleado)) {
            System.out.println("Empleado creado con exito.");
        } else {
            System.out.println("Error al crear empleado.");
        }
    }

    // Pruebas para Motivo
    private static void probarExtraerMotivo(MotivoController motivoController) {
        int idMotivo = 1;
        Motivo motivo = motivoController.extraer(idMotivo);
        if (motivo != null) {
            System.out.println("\nMotivo con ID " + idMotivo + ":");
            System.out.println(motivo);
        } else {
            System.out.println("No se encontró un motivo con el ID " + idMotivo);
        }
    }

    private static void probarExtraerTodoMotivo(MotivoController motivoController) {
        List<Motivo> listaMotivos = motivoController.extraerTodo();
        if (!listaMotivos.isEmpty()) {
            System.out.println("\nLista de motivos:");
            for (Motivo motivo : listaMotivos) {
                System.out.println(motivo);
            }
        } else {
            System.out.println("No se encontraron motivos.");
        }
    }

    private static void probarModificarMotivo(MotivoController motivoController) {
        Motivo motivoAModificar = motivoController.extraer(1);
        if (motivoAModificar != null) {
            motivoAModificar.setMotivo("Nuevo Motivo");
            if (motivoController.modificar(motivoAModificar)) {
                System.out.println("Motivo modificado con exito.");
            } else {
                System.out.println("Error al modificar motivo.");
            }
        } else {
            System.out.println("No se encontró el motivo a modificar.");
        }
    }

    private static void probarEliminarMotivo(MotivoController motivoController) {
        int idMotivo = 4;
        Motivo motivoAEliminar = motivoController.extraer(idMotivo);
        if (motivoAEliminar != null) {
            if (motivoController.eliminar(motivoAEliminar)) {
                System.out.println("Motivo eliminado con exito.");
            } else {
                System.out.println("Error al eliminar motivo.");
            }
        } else {
            System.out.println("No se encontró el motivo con ID " + idMotivo + " para eliminar.");
        }
    }

    private static void probarCrearMotivo(MotivoController motivoController) {
        Motivo nuevoMotivo = new Motivo(5, "Nuevo Motivo");
        if (motivoController.crear(nuevoMotivo)) {
            System.out.println("Motivo creado con exito.");
        } else {
            System.out.println("Error al crear motivo.");
        }
    }

    // Pruebas para TipoAccidente
    private static void probarExtraerTipoAccidente(TipoAccidenteController tipoAccidenteController) {
        int idTipoAccidente = 1;
        TipoAccidente tipoAccidente = tipoAccidenteController.extraer(idTipoAccidente);
        if (tipoAccidente != null) {
            System.out.println("\nTipo de Accidente con ID " + idTipoAccidente + ":");
            System.out.println(tipoAccidente);
        } else {
            System.out.println("No se encontró un tipo de accidente con el ID " + idTipoAccidente);
        }
    }

    private static void probarExtraerTodoTipoAccidente(TipoAccidenteController tipoAccidenteController) {
        List<TipoAccidente> listaTipoAccidentes = tipoAccidenteController.extraerTodo();
        if (!listaTipoAccidentes.isEmpty()) {
            System.out.println("\nLista de tipos de accidentes:");
            for (TipoAccidente tipoAccidente : listaTipoAccidentes) {
                System.out.println(tipoAccidente);
            }
        } else {
            System.out.println("No se encontraron tipos de accidentes.");
        }
    }

    private static void probarModificarTipoAccidente(TipoAccidenteController tipoAccidenteController) {
        TipoAccidente tipoAccidenteAModificar = tipoAccidenteController.extraer(1);
        if (tipoAccidenteAModificar != null) {
            tipoAccidenteAModificar.setTipo("Nuevo Tipo de Accidente");
            if (tipoAccidenteController.modificar(tipoAccidenteAModificar)) {
                System.out.println("Tipo de accidente modificado con exito.");
            } else {
                System.out.println("Error al modificar tipo de accidente.");
            }
        } else {
            System.out.println("No se encontró el tipo de accidente a modificar.");
        }
    }

    private static void probarEliminarTipoAccidente(TipoAccidenteController tipoAccidenteController) {
        int idTipoAccidente = 5;
        TipoAccidente tipoAccidenteAEliminar = tipoAccidenteController.extraer(idTipoAccidente);
        if (tipoAccidenteAEliminar != null) {
            if (tipoAccidenteController.eliminar(tipoAccidenteAEliminar)) {
                System.out.println("Tipo de accidente eliminado con exito.");
            } else {
                System.out.println("Error al eliminar tipo de accidente.");
            }
        } else {
            System.out.println("No se encontró el tipo de accidente con ID " + idTipoAccidente + " para eliminar.");
        }
    }

    private static void probarCrearTipoAccidente(TipoAccidenteController tipoAccidenteController) {
        TipoAccidente nuevoTipoAccidente = new TipoAccidente(5, "Por programar");
        if (tipoAccidenteController.crear(nuevoTipoAccidente)) {
            System.out.println("Tipo de accidente creado con exito.");
        } else {
            System.out.println("Error al crear tipo de accidente.");
        }
    }

    // Pruebas para ParteCuerpo
    private static void probarExtraerParteCuerpo(ParteCuerpoController parteCuerpoController) {
        int idParteCuerpo = 1;
        ParteCuerpo parteCuerpo = parteCuerpoController.extraer(idParteCuerpo);
        if (parteCuerpo != null) {
            System.out.println("\nParte de Cuerpo con ID " + idParteCuerpo + ":");
            System.out.println(parteCuerpo);
        } else {
            System.out.println("No se encontró una parte de cuerpo con el ID " + idParteCuerpo);
        }
    }

    private static void probarExtraerTodoParteCuerpo(ParteCuerpoController parteCuerpoController) {
        List<ParteCuerpo> listaPartesCuerpo = parteCuerpoController.extraerTodo();
        if (!listaPartesCuerpo.isEmpty()) {
            System.out.println("\nLista de partes de cuerpo:");
            for (ParteCuerpo parteCuerpo : listaPartesCuerpo) {
                System.out.println(parteCuerpo);
            }
        } else {
            System.out.println("No se encontraron partes de cuerpo.");
        }
    }

    private static void probarModificarParteCuerpo(ParteCuerpoController parteCuerpoController) {
        ParteCuerpo parteCuerpoAModificar = parteCuerpoController.extraer(1);
        if (parteCuerpoAModificar != null) {
            parteCuerpoAModificar.setParte("Nueva Parte del Cuerpo");
            if (parteCuerpoController.modificar(parteCuerpoAModificar)) {
                System.out.println("Parte del cuerpo modificada con exito.");
            } else {
                System.out.println("Error al modificar parte del cuerpo.");
            }
        } else {
            System.out.println("No se encontró la parte del cuerpo a modificar.");
        }
    }

    private static void probarEliminarParteCuerpo(ParteCuerpoController parteCuerpoController) {
        int idParteCuerpo = 3;
        ParteCuerpo parteCuerpoAEliminar = parteCuerpoController.extraer(idParteCuerpo);
        if (parteCuerpoAEliminar != null) {
            if (parteCuerpoController.eliminar(parteCuerpoAEliminar)) {
                System.out.println("Parte de cuerpo eliminada con exito.");
            } else {
                System.out.println("Error al eliminar parte de cuerpo.");
            }
        } else {
            System.out.println("No se encontró la parte del cuerpo con ID " + idParteCuerpo + " para eliminar.");
        }
    }

    private static void probarCrearParteCuerpo(ParteCuerpoController parteCuerpoController) {
        ParteCuerpo nuevaParteCuerpo = new ParteCuerpo(7, "Nueva Parte del Cuerpo");
        if (parteCuerpoController.crear(nuevaParteCuerpo)) {
            System.out.println("Parte de cuerpo creada con exito.");
        } else {
            System.out.println("Error al crear parte de cuerpo.");
        }
    }

    // Pruebas para ZonaCuerpo
    private static void probarExtraerZonaCuerpo(ZonaCuerpoController zonaCuerpoController) {
        int idZonaCuerpo = 1;
        ZonaCuerpo zonaCuerpo = zonaCuerpoController.extraer(idZonaCuerpo);
        if (zonaCuerpo != null) {
            System.out.println("\nZona de Cuerpo con ID " + idZonaCuerpo + ":");
            System.out.println(zonaCuerpo);
        } else {
            System.out.println("No se encontró una zona de cuerpo con el ID " + idZonaCuerpo);
        }
    }

    private static void probarExtraerTodoZonaCuerpo(ZonaCuerpoController zonaCuerpoController) {
        List<ZonaCuerpo> listaZonasCuerpo = zonaCuerpoController.extraerTodo();
        if (!listaZonasCuerpo.isEmpty()) {
            System.out.println("\nLista de zonas de cuerpo:");
            for (ZonaCuerpo zonaCuerpo : listaZonasCuerpo) {
                System.out.println(zonaCuerpo);
            }
        } else {
            System.out.println("No se encontraron zonas de cuerpo.");
        }
    }

    private static void probarModificarZonaCuerpo(ZonaCuerpoController zonaCuerpoController) {
        ZonaCuerpo zonaCuerpoAModificar = zonaCuerpoController.extraer(1);
        if (zonaCuerpoAModificar != null) {
            zonaCuerpoAModificar.setIzqder(1);
            if (zonaCuerpoController.modificar(zonaCuerpoAModificar)) {
                System.out.println("Zona de cuerpo modificada con exito.");
            } else {
                System.out.println("Error al modificar zona de cuerpo.");
            }
        } else {
            System.out.println("No se encontró la zona de cuerpo a modificar.");
        }
    }

    private static void probarEliminarZonaCuerpo(ZonaCuerpoController zonaCuerpoController) {
        int idZonaCuerpo = 3;
        ZonaCuerpo zonaCuerpoAEliminar = zonaCuerpoController.extraer(idZonaCuerpo);
        if (zonaCuerpoAEliminar != null) {
            if (zonaCuerpoController.eliminar(zonaCuerpoAEliminar)) {
                System.out.println("Zona de cuerpo eliminada con exito.");
            } else {
                System.out.println("Error al eliminar zona de cuerpo.");
            }
        } else {
            System.out.println("No se encontró la zona de cuerpo con ID " + idZonaCuerpo + " para eliminar.");
        }
    }

    private static void probarCrearZonaCuerpo(ZonaCuerpoController zonaCuerpoController) {
        ZonaCuerpo nuevaZonaCuerpo = new ZonaCuerpo(3, 4, 4);
        if (zonaCuerpoController.crear(nuevaZonaCuerpo)) {
            System.out.println("Zona de cuerpo creada con exito.");
        } else {
            System.out.println("Error al crear zona de cuerpo.");
        }
    }
}
