import { useState } from 'react'
import { useNavigate ,Link} from 'react-router-dom'

function Login() {
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')

    // React Router ka tool jo ek page se dusre page bhejta hai
    const navigate = useNavigate()

    const handleLogin = async (e) => {
        e.preventDefault()

        try {
            const response = await fetch('http://localhost:8080/api/users/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ email: email, password: password })
            })

            const data = await response.text()

            // Agar backend se "Successful" message aata hai, toh Home par bhej do
            if (data.includes('Successful')) {
                alert(data)
                navigate('/home')
            } else {
                alert(data) // Wrong password/email par bas alert dikhao
            }

        } catch (error) {
            alert('Error connecting to backend!')
        }
    }

    return (
        <div style={{ textAlign: 'center', marginTop: '50px', fontFamily: 'Arial' }}>
            <h2>🙏 Religious Marketplace</h2>
            <h3>Login to your account</h3>

            <form onSubmit={handleLogin} style={{ display: 'inline-block', textAlign: 'left', padding: '20px', border: '1px solid #ccc', borderRadius: '8px' }}>
                <div style={{ marginBottom: '10px' }}>
                    <label>Email ID: </label><br />
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                        style={{ padding: '5px', width: '200px' }}
                    />
                </div>

                <div style={{ marginBottom: '15px' }}>
                    <label>Password: </label><br />
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                        style={{ padding: '5px', width: '200px' }}
                    />
                </div>

                <button type="submit" style={{ width: '100%', padding: '8px', backgroundColor: '#FF9933', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }}>
                    Login
                </button>
                <p style={{ marginTop: '15px', fontSize: '14px', textAlign: 'center' }}>
                    Don't have an account? <Link to="/signup">Register here</Link>
                </p>
            </form>
        </div>
    )
}

export default Login