export const getMovies = () => {
  const req = new Request("http://localhost:8080/api/movies",{
    headers:{
      method: "GET",
      credentials: "same-origin"
    }
  });
  return fetch(req).then((response) => {return response.json()});
};
