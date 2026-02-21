import {use, useEffect, useState} from "react";
import UserProfile from "./UserProfile.jsx";

import SidebarWithHeader from "./shared/SideBar.jsx";


//
// const UserProfiles = ({users}) => (
//   <div>
//     {
//       users.map((user,  index) => (
//           <UserProfile
//             key={index}
//             name={user.name}
//             age={user.age}
//             gender={user.gender}
//           />
//       ))}
//   </div>
// );

function App() {

  return (
    <SidebarWithHeader/>
  );
}

export default App
