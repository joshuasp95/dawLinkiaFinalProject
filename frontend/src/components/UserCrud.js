// src/components/UserCrud.js
import React, { useEffect, useState } from "react";
import {
  Typography,
  Container,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Button,
  IconButton,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  TextField,
  CircularProgress,
  Alert,
} from "@mui/material";
import { Edit, Delete } from "@mui/icons-material";
import {
  getUsers,
  createUser,
  updateUser,
  deleteUser,
} from "../services/userService";

const UserCrud = () => {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Estados para el diálogo de crear/editar
  const [openDialog, setOpenDialog] = useState(false);
  const [isEditMode, setIsEditMode] = useState(false);
  const [currentUser, setCurrentUser] = useState({
    id: "",
    nombre: "",
    apellidos: "",
    email: "",
    password: "",
  });

  // Estados para manejar mensajes de éxito
  const [success, setSuccess] = useState("");

  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    try {
      const response = await getUsers();
      console.log("Respuesta de getUsers:", response.data); // Para depuración
      setUsers(response.data.data);
      setLoading(false);
    } catch (err) {
      console.error(err);
      setError("Error al obtener la lista de usuarios.");
      setLoading(false);
    }
  };

  const handleOpenDialog = (
    user = { id: "", nombre: "", apellidos: "", email: "", password: "" }
  ) => {
    if (user.id) {
      setIsEditMode(true);
      setCurrentUser(user);
    } else {
      setIsEditMode(false);
      setCurrentUser({
        id: "",
        nombre: "",
        apellidos: "",
        email: "",
        password: "",
      });
    }
    setOpenDialog(true);
  };

  const handleCloseDialog = () => {
    setOpenDialog(false);
    setError(null);
  };

  const handleChange = (e) => {
    setCurrentUser({ ...currentUser, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);
    setSuccess("");

    // Validaciones básicas
    if (
      !currentUser.nombre ||
      !currentUser.apellidos ||
      !currentUser.email ||
      (!isEditMode && !currentUser.password)
    ) {
      setError("Por favor, completa todos los campos.");
      return;
    }

    try {
      if (isEditMode) {
        const updatedUser = { ...currentUser };
        if (!updatedUser.password) {
          delete updatedUser.password; // No enviar la contraseña si no se ha modificado
        }
        await updateUser(updatedUser.id, updatedUser);
        setUsers(
          users.map((user) => (user.id === updatedUser.id ? updatedUser : user))
        );
        setSuccess("Usuario actualizado exitosamente.");
      } else {
        const response = await createUser(currentUser);
        console.log("Respuesta de createUser:", response.data); // Depuración
        setUsers([...users, response.data.data]); // Añade el nuevo usuario
        setSuccess("Usuario creado exitosamente.");
      }
      handleCloseDialog();
    } catch (err) {
      console.error(err);
      setError("Error al guardar el usuario.");
    }
  };

  const handleDelete = async (userId) => {
    if (window.confirm("¿Estás seguro de que deseas eliminar este usuario?")) {
      try {
        await deleteUser(userId);
        setUsers(users.filter((user) => user.id !== userId));
        setSuccess("Usuario eliminado exitosamente.");
      } catch (err) {
        console.error(err);
        setError("Error al eliminar el usuario.");
      }
    }
  };

  return (
    <Container>
      <Typography variant="h4" sx={{ mt: 4, mb: 2 }}>
        Gestión de Usuarios
      </Typography>
      {loading ? (
        <CircularProgress />
      ) : error ? (
        <Alert severity="error" sx={{ mb: 2 }}>
          {error}
        </Alert>
      ) : (
        <>
          {success && (
            <Alert severity="success" sx={{ mb: 2 }}>
              {success}
            </Alert>
          )}
          <Button
            variant="contained"
            color="primary"
            onClick={() => handleOpenDialog()}
            sx={{ mb: 2 }}
          >
            Añadir Usuario
          </Button>
          <TableContainer component={Paper}>
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell>Nombre</TableCell>
                  <TableCell>Apellidos</TableCell>
                  <TableCell>Correo Electrónico</TableCell>
                  <TableCell align="right">Acciones</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {users.map((user) => (
                  <TableRow key={user.id}>
                    <TableCell>{user.nombre}</TableCell>
                    <TableCell>{user.apellidos}</TableCell>
                    <TableCell>{user.email}</TableCell>
                    <TableCell align="right">
                      <IconButton
                        color="primary"
                        onClick={() => handleOpenDialog(user)}
                      >
                        <Edit />
                      </IconButton>
                      <IconButton
                        color="secondary"
                        onClick={() => handleDelete(user.id)}
                      >
                        <Delete />
                      </IconButton>
                    </TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        </>
      )}

      {/* Diálogo para Crear/Editar Usuario */}
      <Dialog open={openDialog} onClose={handleCloseDialog}>
        <DialogTitle>
          {isEditMode ? "Editar Usuario" : "Añadir Usuario"}
        </DialogTitle>
        <DialogContent>
          <form onSubmit={handleSubmit}>
            <TextField
              margin="dense"
              label="Nombre"
              name="nombre"
              fullWidth
              required
              value={currentUser.nombre}
              onChange={handleChange}
            />
            <TextField
              margin="dense"
              label="Apellidos"
              name="apellidos"
              fullWidth
              required
              value={currentUser.apellidos}
              onChange={handleChange}
            />
            <TextField
              margin="dense"
              label="Correo Electrónico"
              name="email"
              type="email"
              fullWidth
              required
              value={currentUser.email}
              onChange={handleChange}
              error={
                !/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(
                  currentUser.email
                )
              }
              helperText={
                !/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(
                  currentUser.email
                )
                  ? "Correo electrónico inválido"
                  : ""
              }
            />

            <TextField
              margin="dense"
              label="Contraseña"
              name="password"
              type="password"
              fullWidth
              required={!isEditMode} // Requerido solo en creación
              value={currentUser.password}
              onChange={handleChange}
              placeholder={isEditMode ? "Deja en blanco para no cambiar" : ""}
            />
            {error && (
              <Alert severity="error" sx={{ mt: 2 }}>
                {error}
              </Alert>
            )}
            {success && (
              <Alert severity="success" sx={{ mb: 2 }}>
                {success}
              </Alert>
            )}
          </form>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCloseDialog}>Cancelar</Button>
          <Button onClick={handleSubmit} variant="contained" color="primary">
            {isEditMode ? "Guardar Cambios" : "Crear Usuario"}
          </Button>
        </DialogActions>
      </Dialog>
    </Container>
  );
};

export default UserCrud;
