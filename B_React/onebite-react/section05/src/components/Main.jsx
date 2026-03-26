const Main = () => {
  const user = {
    name: "이망고",
    isLogin: true
  }
  return (
    <main>
      {user.isLogin ? (
        <div>로그아웃</div>
      ) : (
        <div>로그인</div>
      )}
    </main>
  )
}

export default Main;